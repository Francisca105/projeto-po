package xxl;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

import xxl.content.Content;
import xxl.content.functions.binary.Add;
import xxl.content.functions.binary.Div;
import xxl.content.functions.binary.Mul;
import xxl.content.functions.binary.Sub;
import xxl.content.functions.interval.nospaces.Avg;
import xxl.content.functions.interval.nospaces.Prod;
import xxl.content.functions.interval.spaces.Coal;
import xxl.content.functions.interval.spaces.Conc;
import xxl.content.literals.Int;
import xxl.content.literals.Str;
import xxl.content.Reference;
import xxl.datastructure.DataStructure;
import xxl.datastructure.CellsMap;
import xxl.exceptions.InvalidDimensionException;
import xxl.exceptions.InvalidGammaException;
import xxl.exceptions.ParseFunctionException;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.visits.CellVisitor;
import xxl.visitors.RenderContent;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;

    /** Name of the spreadsheet. */
    private String _name = "";

    /** Data structure storing the cells of the spreadsheet. */
    private CellsMap _cells;

    /** Cut buffer of the spreadsheet. */
    private DataStructure _cutBuffer;

    /** Boolean indicating if the spreadsheet object has been changed. */
    private boolean _hasChanged;

    /** Collection of users of the spreadsheet. */
    private Map<String, User> _user = new HashMap<String, User>();

    /**
     * Constructor.
     *
     * @param rows
     * @param columns
     */
    public Spreadsheet(int rows, int columns) throws InvalidDimensionException {
        if (rows <= 0 || columns <= 0)
            throw new InvalidDimensionException();
        _cells = new CellsMap(rows, columns);
        setToSave(true);
    }

    /**
     * 
     * @return the users of the spreadsheet
     */
    public Map<String, User> getUsers() {
        return _user;
    }

    /**
     * 
     * @return the name of the spreadsheet
     */
    public String getName() {
        return _name;
    }

    /**
     * 
     * @return the data structure storing the cells of the spreadsheet
     */
    public DataStructure getCells() {
        return _cells;
    }

    /**
     * 
     * @return the saved status
     */
    public boolean getToSave() {
        return _hasChanged;
    }

    /**
     * Sets the saved status.
     * 
     * @param hasChanged
     */
    public void setToSave(boolean hasChanged) {
        _hasChanged = hasChanged;
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
     * Searches in the spreadsheet for a cell that satisfies the given condition.
     */
    public void search(CellVisitor visitor, String string) {
        for (Map.Entry<Address, Cell> entry : _cells.getAllCells().entrySet()) {
                Address address = entry.getKey();
                Cell cell = entry.getValue();             
                cell.accept(visitor, address.toString());
            }
    }

    /**
     * Insert specified content in specified range.
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    public void insertContents(String rangeSpecification, String contentSpecification, boolean bool) throws UnrecognizedEntryException, ParseFunctionException, InvalidGammaException {
        try {
            Gamma gamma = new Gamma(rangeSpecification);
            Content content = parseContent(contentSpecification, gamma, bool);
            setContentCell(gamma, content, bool);
            setToSave(true);
        } catch (NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses a string content as a Content.
     * 
     * @param content
     * @param gamma (useful only if the content is a function or a reference)
     * @param bool  (differentiates between the spreadsheet(true) and the cut buffer(false))
     * @return the string content as a Content object
     */
    public Content parseContent(String content, Gamma gamma, boolean bool) throws InvalidGammaException, ParseFunctionException {
        if (content == null || content.length() == 0)
            return null;
        else if (content.startsWith("="))
            return parseContentExpression(content, gamma, bool);
        else
            return parseContentLiteral(content);
    }

    /**
     * Parses a string content as a Function or Reference.
     * 
     * @param content
     * @param gamma
     * @param bool
     * @return the string content as a Function or Reference object
     */
    public Content parseContentExpression(String content, Gamma gamma, boolean bool) throws InvalidGammaException, ParseFunctionException {
        if (content.contains("("))
            return parseContentFunction(content, gamma, bool);
        else
            return parseContentReference(content.substring(1), gamma, bool);
    }

    /**
     * Parses a string content as a Function.
     *
     * @param content
     * @param gamma
     * @param bool 
     * @return the string content as a Function object
     */
    public Content parseContentFunction(String content, Gamma gamma, boolean bool) throws InvalidGammaException, ParseFunctionException {
        String functionName = content.substring(1, content.indexOf("("));

        String[] binaryFunctions = {"ADD", "DIV", "MUL", "SUB"};
        String[] intervalFunctions = {"AVERAGE", "PRODUCT", "CONCAT", "COALESCE"};

        for (String string : intervalFunctions)
            if(functionName.equals(string))
                return parseIntervalFunction(functionName, content, gamma, bool);

        for (String string : binaryFunctions)
            if(functionName.equals(string))
                return parseBinaryFunction(functionName, content, gamma, bool);

        throw new ParseFunctionException(functionName);     
    }

    /**
     * Parses a string content as a BinaryFunction.
     *
     * @param functionName
     * @param content
     * @param gamma
     * @param bool 
     * @return the string content as a binary function object of the given functionName
     */
    public Content parseBinaryFunction(String functionName, String content, Gamma gamma, boolean bool) throws InvalidGammaException {
        String[] args = content.substring(content.indexOf("(")+1, content.length()-1).split(",");

        Content arg1 = parseBinaryArgument(args[0], gamma, bool);
        Content arg2 = parseBinaryArgument(args[1], gamma, bool);

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
     * @param gamma
     * @param bool
     * @return the string content as a Content object
     */
    public Content parseBinaryArgument(String content, Gamma gamma, boolean bool) throws InvalidGammaException {
        if (content.length() == 0)
            return null;
        else if (content.contains(";") && !content.startsWith("'"))
            return parseContentReference(content, gamma, bool);
        else
            return parseContentLiteral(content);
    }

    /**
     * Parses a string content as an IntervalFunction.
     * 
     * @param functionName
     * @param content
     * @param gamma
     * @param bool
     * @return the string content as an interval function object of the given functionName
     */
    public Content parseIntervalFunction(String functionName, String content, Gamma gamma, boolean bool) throws InvalidGammaException {
        content = content.substring(content.indexOf("(")+1, content.length()-1);
        Range range = new Range(content);

        if (bool)
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

    /**
     * Parses a string content as a Reference.
     *
     * @param content
     * @param gamma
     * @param bool
     * @return the string content as a Reference object
     */
    public Content parseContentReference(String content, Gamma gamma, boolean bool) throws InvalidGammaException {
        Reference reference = new Reference(new Address(content), _cells);
        if (bool)
            for (Address address : gamma.getAddresses())
                reference.getCell().registerObserver(_cells.getCell(address));
        return reference;
    }

    /**
     * Parses a string content as a Literal.
     * 
     * @param content
     * @return the string content as a literal object of the correspondent type
     */
    public Content parseContentLiteral(String content) {
        if (content.charAt(0) == '\'')
            return new Str(content.substring(1));
        else
            return new Int(Integer.parseInt(content));
    }

    /**
     * Deletes the content of the cells in the given gamma.
     * 
     * @param gamma
     */
    public void deleteGamma(String gamma) throws InvalidGammaException {
        Gamma _gamma = new Gamma(gamma);
        if (!_gamma.isValid(_cells))
            throw new InvalidGammaException(gamma);
        for (Address address : _gamma.getAddresses()) {
            Cell cell = _cells.getCell(address);
            cell.unsubscribe();
            cell.setContent(null);
        }
    }

    /**
     * Accepts a visitor for the cells of the given range in order to render them.
     * 
     * @param visitor
     * @param range
     */
    public void showRange(CellVisitor visitor, String range) throws InvalidGammaException {
        Gamma gamma = new Gamma(range);

        for (Address address : gamma.getAddresses()) {
            Cell cell = _cells.getCell(address);

            if (cell == null)
                throw new InvalidGammaException(range);

            cell.accept(visitor, address.toString());
        }
    }

    /* CUT BUFFER */

    /**
     * Accepts a visitor for all the cells of the cut buffer in order to render them.
     * 
     * @param visitor
     */
    public void showCutBuffer(CellVisitor visitor) {
        if (_cutBuffer != null)
            for (Map.Entry<Address, Cell> entry : _cutBuffer.getAllCells().entrySet()) {
                Address address = entry.getKey();
                Cell cell = entry.getValue();                
                cell.accept(visitor, address.toString());
            }
    }

    /**
     * Copies the cells in the given gamma to the cut buffer.
     * 
     * @param range
     */
    public void copy(String range) throws InvalidGammaException, UnrecognizedEntryException, ParseFunctionException {
        Gamma gamma = new Gamma(range);
        if (!gamma.isValid(_cells))
            throw new InvalidGammaException(range);
        _cutBuffer = new CellsMap();

        int i = 1;
        int j = 1;
        RenderContent content = new RenderContent();

        for (Address address : gamma.getAddresses()) {
            Cell cell = _cells.getCell(address);
            String s = i + ";" + j;
            _cutBuffer.getAllCells().put(new Address(i, j), new Cell());
            try {
                cell.accept(content, "");
                insertContents(s, content.getRender(), false);
            } catch (NullPointerException e) {
                insertContents(s, "", false);
            }

            if(gamma.isColumn())
                i++;
            else
                j++;
        }
        _cutBuffer.setRows(i);
        _cutBuffer.setColumns(j);
    }

    /**
     * Copies the cells in the given gamma to the cut buffer and deletes their content from the spreadsheet.
     * 
     * @param gamma
     */
    public void cut(String gamma) throws InvalidGammaException, UnrecognizedEntryException, ParseFunctionException {
        copy(gamma);
        deleteGamma(gamma);
    }
    
    /**
     * Pastes the content of the cut buffer in the given range.
     * 
     * @param range
     */
    public void paste(String range) throws InvalidGammaException, UnrecognizedEntryException, ParseFunctionException {
        if(_cutBuffer == null || _cutBuffer.getAllCells().size() == 0)
            return;
        Map<Address, Cell> allCells = _cutBuffer.getAllCells();
        
        Gamma gamma = new Gamma(range);
        if (!gamma.isValid(_cells))
            throw new InvalidGammaException(range);
        Address[] addresses = gamma.getAddresses();

        RenderContent content = new RenderContent();

        if(addresses.length > 1 && allCells.size() != addresses.length)
            throw new InvalidGammaException(range);

        if (addresses.length == 1) {
            Address first = addresses[0];
            Address last;
            if(_cutBuffer.getColumns() == 1)
                last = new Address(_cells.getRows(), first.getColumn());
            else
                last = new Address(first.getRow(), _cells.getColumns());
            
            gamma = new Gamma(first.toString() + ":" + last.toString()); 
            addresses = gamma.getAddresses();
        } else {
            Address first = addresses[0];
            Address second = addresses[1];
            if(first.getRow() == second.getRow() && _cutBuffer.getRows() != 1)
                throw new InvalidGammaException(range);
            else if (first.getColumn() == second.getColumn() && _cutBuffer.getColumns() != 1)
                throw new InvalidGammaException(range);
        }

        int i = 1;
        int j = 1;

        for (Address address : addresses) {
            Cell cell = _cutBuffer.getCell(new Address(i, j));
            try {
                cell.accept(content, "");
                insertContents(address.toString(), content.getRender(), true);
            } catch (NullPointerException e) {
                insertContents(address.toString(), "", true);
            }

            if(_cutBuffer.getColumns() != 1) {
                if(j == _cutBuffer.getColumns()-1)
                    j = 1;
                else
                    j++;
            } else {
                if(i == _cutBuffer.getRows()-1)
                    i = 1;
                else
                    i++;
            }
        }
    }

    /**
     * Sets the content of the cells in the given gamma.
     * 
     * @param range
     * @param content
     * @param bool
     */
    public void setContentCell(Gamma gamma, Content content, boolean bool) throws InvalidGammaException {
        if (gamma.getAddress() != null)
            setContentCell(gamma.getAddress(), content, bool);
         else
            setContentCell(gamma.getRange(), content, bool);
    }

    /**
     * Sets the content of the cell at the given address.
     * 
     * @param address
     * @param content
     * @param bool
     */
    public void setContentCell(Address address, Content content, boolean bool) throws InvalidGammaException{
        Cell cell;
        if (bool) {
            cell = _cells.getCell(address);
            cell.unsubscribe();
        }
        else
            cell = _cutBuffer.getCell(address);
        cell.setContent(content);
    }

    /**
     * Sets the content of the cells in the given range.
     * 
     * @param range
     * @param content
     * @param bool
     */
    public void setContentCell(Range range, Content content, boolean bool) throws InvalidGammaException {
        if (bool)
            for (Cell c : _cells.getCells(range)) {
                c.unsubscribe();
                c.setContent(content);
            }
        else
            for (Cell c : _cutBuffer.getCells(range))
                c.setContent(content);
    }
}