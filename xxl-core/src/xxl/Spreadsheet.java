package xxl;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.function.Predicate;

import xxl.content.functions.binary.Add;
import xxl.content.functions.binary.Div;
import xxl.content.functions.binary.Mul;
import xxl.content.functions.binary.Sub;
import xxl.content.functions.interval.nospaces.Avg;
import xxl.content.functions.interval.nospaces.Prod;
import xxl.content.functions.interval.spaces.Coal;
import xxl.content.functions.interval.spaces.Conc;
import xxl.content.functions.Function;
import xxl.content.literals.Int;
import xxl.content.literals.Str;
import xxl.content.Content;
import xxl.content.Reference;
import xxl.datastructure.CellsMap;
import xxl.datastructure.DataStructure;
import xxl.exceptions.InvalidDimensionException;
import xxl.exceptions.InvalidGammaException;
import xxl.exceptions.ParseFunctionException;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.visits.CellVisitor;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;

    /** Name of the spreadsheet. */
    private String _name = "";

    /** Data structure of the spreadsheet */
    private CellsMap _cells;

    /** Cut buffer of the spreadsheet */
    private DataStructure _cutBuffer;

    /** Spreadsheet object has been changed. */
    private boolean _toSave;

    /** Collection of users of the spreadsheet. */
    private Map<String, User> _user = new HashMap<String, User>();

    /**
     * Constructor.
     *
     * @param rows
     * @param columns
     */
    public Spreadsheet(int rows, int columns) throws InvalidDimensionException {
        if (rows <= 0 || columns <= 0) {
            throw new InvalidDimensionException();
        }
        _cells = new CellsMap(rows, columns);
    }

    /**
     * 
     * @return the data structure representing the spreadsheet
     */
    public DataStructure getCells() {
        return _cells;
    }

//    /**
//     * 
//     * @return the data structure representing the spreadsheet
//     */
//    public Collection<String> showCellsRange(String range) throws InvalidGammaException {
//        return getCells().showRange(range);
//    }


    /**
     * 
     * @return the name of the spreadsheet
     */
    public String getName() {
        return _name;
    }

    /**
     * 
     * @return the saved status
     */
    public boolean getToSave() {
        return _toSave;
    }

    /**
     * Sets the saved status.
     * 
     * @param toSave
     * @return 
     */
    public void setToSave(boolean toSave) {
        _toSave = toSave;
    }

    /**
     * Sets the name of the spreadsheet.
     * 
     * @param name
     * @return 
     */
    public void setName(String name) {
        _name = name;
    }

    /**
     * 
     * @return the list of an searched item
     */
    public Collection<String> search(Predicate<Cell> predicate, CellVisitor visitor) {
        Collection<String> result = new ArrayList<String>();

        for (Map.Entry<Address, Cell> entry : _cells.getAllCells().entrySet()) {
            Address address = entry.getKey();
            Cell cell = entry.getValue();
            if (predicate.test(cell)) {
                cell.accept(visitor, address.toString());
            }
        }

        return result;
    }

    /**
     * Searches in the spreadsheet for a function name.
     * 
     * @param value
     * @return the list of the searched items
     */
    public Collection<String> searchF(String value, CellVisitor visitor) {
        return search(cell -> {
            boolean condition = false;

            try {
                condition = ((Function) (cell.getContent())).getName().startsWith(value);
            } catch (ClassCastException | NullPointerException e) {
                 //do nothing 
            }
            // Todo: comparar o nome da função com um enum das funções para lançar a UnknowFunctionException
            return condition;
        }, visitor);
    }

    /**
     * Searches in the spreadsheet for a literal value.
     * 
     * @param value
     * @return the list of the searched items
     */
    public Collection<String> searchV(String value, CellVisitor visitor) {
        return search(cell -> cell.value().toString().equals(value), visitor);
    }

    /**
     * Insert specified content in specified range.
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    public void insertContents(String rangeSpecification, String contentSpecification, boolean bool) throws UnrecognizedEntryException, ParseFunctionException {
        try {
            Gamma gamma = new Gamma(rangeSpecification);
            //Address address = new Address(rangeSpecification);
            Content content = parseContent(contentSpecification, gamma);
            //_cells.setContentCell(address, content);
            if (bool)
                setContentCell(gamma, content, _cells);
            else
                setContentCell(gamma, content, _cutBuffer);
            setToSave(true);
        } catch (InvalidGammaException | NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses a string content as a Content.
     * 
     * @return the string content as a Content object
     */
    public Content parseContent(String content, Gamma gamma) throws InvalidGammaException, ParseFunctionException {
        if (content == null || content.length() == 0) {
            return null;
        } else if (content.startsWith("=")) {
            return parseContentExpression(content, gamma);
        } else {
            return parseContentLiteral(content);
        }
    }

    /**
     * Parses a string content as a Function or Reference.
     * 
     * @return the string content as a Function or Reference object
     */
    public Content parseContentExpression(String content, Gamma gamma) throws InvalidGammaException, ParseFunctionException {
        if (content.contains("(")) {
            return parseContentFunction(content, gamma);
        } else {
            return parseContentReference(content.substring(1), gamma);
        }
    }

    /**
     * Parses a string content as a Function.
     * 
     * @return the string content as a Function object
     */
    public Content parseContentFunction(String content, Gamma gamma) throws InvalidGammaException, ParseFunctionException {
        String functionName = content.substring(1, content.indexOf("("));

        String[] binaryFunctions = {"ADD", "DIV", "MUL", "SUB"};
        String[] intervalFunctions = {"AVERAGE", "PRODUCT", "CONCAT", "COALESCE"};

        for (String string : intervalFunctions) {
            if(functionName.equals(string))
                return parseIntervalFunction(functionName, content, gamma);
        }

        for (String string : binaryFunctions) {
            if(functionName.equals(string))
                return parseBinaryFunction(functionName, content, gamma);
        }
        
        throw new ParseFunctionException(functionName);
        
    }

    public Content parseBinaryFunction(String functionName, String content, Gamma gamma) throws InvalidGammaException {
        String[] args = content.substring(content.indexOf("(")+1, content.length()-1).split(",");

        Content arg1 = parseBinaryArgument(args[0], gamma);
        Content arg2 = parseBinaryArgument(args[1], gamma);

        switch (functionName) {
            case "ADD":
                return new Add(arg1, arg2);
            case "DIV":
                return new Div(arg1, arg2);
            case "MUL":
                return new Mul(arg1, arg2);
            case "SUB":
                return new Sub(arg1, arg2);
        }
        return new Str(content);
    }

    /**
     * Auxiliar method to parse the content of a function argument.
     * 
     * @param content
     * @return the content parsed as a Content object
     * @throws InvalidGamaException
     */
    public Content parseBinaryArgument(String content, Gamma gamma) throws InvalidGammaException {
        if (content.length() == 0) {
            return null;
        } else if (content.contains(";") && !content.startsWith("'")) {
            return parseContentReference(content, gamma);
        } else {
            return parseContentLiteral(content);
        }
    }

    /**
     * Parses a string content as a Reference.
     * 
     * @return the string content as a Reference object
     */
    public Content parseContentReference(String content, Gamma gamma) throws InvalidGammaException {
        Reference reference = new Reference(new Address(content), _cells);
        for (Address address : gamma.getAddresses())
            reference.getCell().registerObserver(_cells.getCell(address));
        return reference;
    }

    /**
     * Parses a string content as a Literal.
     * 
     * @return the string content as a Literal object
     */
    public Content parseContentLiteral(String content) {
        if (content.charAt(0) == '\'') {
            return new Str(content.substring(1));
        } else {
            return new Int(Integer.parseInt(content));
        }
    }

    public Content parseIntervalFunction(String functionName, String content, Gamma gamma) throws InvalidGammaException {
        content = content.substring(content.indexOf("(")+1, content.length()-1);
        Range range = new Range(content);

        for (Cell cell : _cells.getCells(range))
            cell.registerObserver(_cells.getCell(gamma.getAddress()));

        switch (functionName) {
            case "AVERAGE":
                return new Avg(range, _cells);
            case "PRODUCT":
                return new Prod(range, _cells);
            case "CONCAT":
                return new Conc(range, _cells);
            case "COALESCE":
                return new Coal(range, _cells);
        }

        return new Str(content);
    }

    public void deleteRange(String range) throws InvalidGammaException {
        Gamma gamma = new Gamma(range);

        for (Address address : gamma.getAddresses()) {
            Cell cell = _cells.getCell(address);
            cell.unsubscribe();
            cell.setContent(null);
        }
    }

    // Show range of cells
    public void acceptCellsRangeVisitor(CellVisitor visitor, String range) throws InvalidGammaException {
        Gamma gamma = new Gamma(range);

        for (Address address : gamma.getAddresses()) {
            Cell cell = _cells.getCell(address);

            if (cell == null)
                throw new InvalidGammaException(range);

            cell.accept(visitor, address.toString());
        }
    }

    /* CUT BUFFER */

    // Show cut buffer
    public void acceptCellsVisitor(CellVisitor visitor) {
        if (_cutBuffer != null) {
            for (Map.Entry<Address, Cell> entry : _cutBuffer.getAllCells().entrySet()) {
                Address address = entry.getKey();
                Cell cell = entry.getValue();
                
                cell.accept(visitor, address.toString());
            }
        }
    }

    public void copy(String range) throws InvalidGammaException, UnrecognizedEntryException, ParseFunctionException  {
        // TODO: apanhar a exceção InvalidGammaException e lançar a InvalidCellRangeException na App
        Gamma gamma = new Gamma(range);
        _cutBuffer = new CellsMap();

        int i = 1;
        int j = 1;

        for (Address address : gamma.getAddresses()) {
//            Cell cell = _cells.getCell(address);
//            String s = i + ";" + j;
//            insertContents(s, cell.getContent().string(), false);
            Cell copy = new Cell(_cells.getCell(address));
            _cutBuffer.getAllCells().put(new Address(j, i), copy);

            if(gamma.isColumn())
                j++;
            else
                i++;
        }
    }

//    public void cop(String range) throws InvalidGammaException{
//        Gamma gamma = new Gamma(range);
//        Address[] addresses = gamma.getAddresses();
//        int n = addresses.length;
//        if (gamma.isColumn()) {
//            n = addresses[n-1].getRow() - addresses[0].getRow() + 1;
//            _cutBuffer = new CellsMap(n, 1);
//        }
//        else {
//            n = addresses[n-1].getRow() - addresses[0].getRow() + 1;
//            _cutBuffer = new CellsMap(1, n);
//        }
//        for (Address address : addresses) {
//            insertContents()
//        }
//    }

    public void cut(String range) throws InvalidGammaException, UnrecognizedEntryException, ParseFunctionException {
        copy(range);
        deleteRange(range);
    }
    
    public void paste(String range) throws InvalidGammaException {
        if(_cutBuffer == null || _cutBuffer.getAllCells().size() == 0)
            return;

        Gamma gamma = new Gamma(range);
        Address[] addresses = gamma.getAddresses();
        
        if(_cutBuffer.getAllCells().size() == 1) {
            for (Address address : addresses) {
                Cell cell = _cutBuffer.getCell(new Address(1, 1));
                cell.unsubscribe();
                _cells.getCell(address).setContent(cell.getContent());
            }
        } else {
            if (_cutBuffer.getAllCells().size() > 1 && addresses.length == 1) {
                Address first = addresses[0];
                gamma = new Gamma(first.toString() + ":" + new Address(first.getColumn()+":"+first.getRow()).toString());
            }
        } 
        
        if (_cutBuffer.getAllCells().size() == addresses.length) {
            int i = 1;
            int j = 1;

            for (Address address : addresses) {
                Cell cell = _cutBuffer.getCell(new Address(j, i));
                cell.unsubscribe();
                _cells.getCell(address).setContent(cell.getContent());

                if(gamma.isColumn())
                    j++;
                else
                    i++;
            }
        } else  {

        }
        
    }

    /**
     * Sets the content of the cells in the given gamma.
     * 
     * @param range
     * @param content
     */
    public void setContentCell(Gamma gamma, Content content, DataStructure dataStructure) throws InvalidGammaException{
        if (gamma.getAddress() != null) {
            setContentCell(gamma.getAddress(), content, dataStructure);
        } else {
            setContentCell(gamma.getRange(), content, dataStructure);
        }
    }

    /**
     * Sets the content of the cell at the given address.
     * 
     * @param address
     * @param content
     */
    public void setContentCell(Address address, Content content, DataStructure dataStructure) throws InvalidGammaException{
        Cell cell = dataStructure.getCell(address);
        cell.unsubscribe();
        cell.setContent(content);
    }

    /**
     * Sets the content of the cells in the given range.
     * 
     * @param range
     * @param content
     */
    public void setContentCell(Range range, Content content, DataStructure dataStructure) throws InvalidGammaException{
        for (Cell c : dataStructure.getCells(range)) {
            c.unsubscribe();
            c.setContent(content);
        }
    }
}