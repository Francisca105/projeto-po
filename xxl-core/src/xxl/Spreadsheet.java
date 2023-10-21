package xxl;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.TreeMap;


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
import xxl.content.literals.Literal;
import xxl.content.Content;
import xxl.content.Reference;
import xxl.datastructure.CellsMap;
import xxl.datastructure.DataStructure;
import xxl.exceptions.InvalidDimensionException;
import xxl.exceptions.InvalidGammaException;
import xxl.exceptions.UnrecognizedEntryException;

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

    /**
     * 
     * @return the data structure representing the spreadsheet
     */
    public Collection<String> showCellsRange(String range) throws InvalidGammaException {
        return getCells().showRange(range);
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
    public Collection<String> search(Predicate<Cell> predicate) {
        Collection<String> result = new ArrayList<String>();

        for (Map.Entry<Address, Cell> entry : _cells.getAllCells().entrySet()) {
            Address address = entry.getKey();
            Cell cell = entry.getValue();
            if (predicate.test(cell)) {
                Content content = cell.getContent();

                if(content != null) 
                    result.add(address.toString() + "|" + content.showValue());
                else
                    result.add(address.toString() + "|");
            }
        }

        return result;
    }

    public Collection<String> search(String value) {
        return search(cell -> cell.getValue().showValue().equals(value));
    }

    /**
     * Insert specified content in specified range.
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    public void insertContents(String rangeSpecification, String contentSpecification) throws UnrecognizedEntryException {
        try {
            Address address = new Address(rangeSpecification);
            Content content = parseContent(contentSpecification);
            _cells.setContentCell(address, content);
        } catch (InvalidGammaException | NumberFormatException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses a string content as a Content.
     * 
     * @return the string content as a Content object
     */
    public Content parseContent(String content) throws InvalidGammaException {
        if (content == null || content.length() == 0) {
            return null;
        } else if (content.startsWith("=")) {
            return parseContentExpression(content);
        } else {
            return parseContentLiteral(content);
        }
    }

    /**
     * Parses a string content as a Function or Reference.
     * 
     * @return the string content as a Function or Reference object
     */
    public Content parseContentExpression(String content) throws InvalidGammaException {
        if (content.contains("(")) {
            return parseContentFunction(content);
        } else {
            return parseContentReference(content.substring(1));
        }
    }

    /**
     * Parses a string content as a Function.
     * 
     * @return the string content as a Function object
     */
    public Content parseContentFunction(String content) throws InvalidGammaException {
        String functionName = content.substring(1, content.indexOf("("));
        String[] args = content.substring(content.indexOf("(")+1, content.length()-1).split(",");

        Content arg1 = parseArgumentContent(args[0]);
        Content arg2 = parseArgumentContent(args[1]);

        switch (functionName) {
            case "ADD":
                return new Add(arg1, arg2);
            case "DIV":
                return new Div(arg1, arg2);
            case "MUL":
                return new Mul(arg1, arg2);
            case "SUB":
                return new Sub(arg1, arg2);
            case "AVERAGE ":
                return new Avg(arg1, arg2);
            case "PRODUCT":
                return new Prod(arg1, arg2);
            case "CONCAT":
                return new Conc(arg1, arg2);
            case "COALESCE":
                return new Coal(arg1, arg2);
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
    public Content parseArgumentContent(String content) throws InvalidGammaException {
        if (content.length() == 0) {
            return null;
        } else if (content.contains(";") && !content.startsWith("'")) {
            return parseContentReference(content);
        } else {
            return parseContentLiteral(content);
        }
    }

    /**
     * Parses a string content as a Reference.
     * 
     * @return the string content as a Reference object
     */
    public Content parseContentReference(String content) throws InvalidGammaException {
        Address address = new Address(content);
        return new Reference(address, _cells);
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
}
