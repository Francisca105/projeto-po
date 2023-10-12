package xxl;
// FIXME import classes

import java.io.Serial;
import java.io.Serializable;

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
import xxl.content.Content;
import xxl.content.Reference;
import xxl.datastructure.CellsMap;
import xxl.datastructure.DataStructure;
import xxl.exceptions.InvalidGamaException;
import xxl.exceptions.UnrecognizedEntryException;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;
    // FIXME define attributes
    // FIXME define contructor(s)
    // FIXME define methods

    /** Name of the spreadsheet. */
    private String _name = "";

    /** Number of the spreadsheet rows. */
    private int _rows;

    /** Number of the spreadsheet columns. */
    private int _columns;

    /** Data structure of the spreadsheet */
    private DataStructure _cells;

    /** Cut buffer of the spreadsheet */
    private DataStructure _cutBuffer;

    /**
     * Constructor.
     *
     * @param rows
     * @param columns
     */
    public Spreadsheet(int rows, int columns) {
        _rows = rows;
        _columns = columns;
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
     * Insert specified content in specified range.
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    public void insertContents(String rangeSpecification, String contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
        try {
            Address address = new Address(rangeSpecification);
            Content content = parseContent(contentSpecification);


        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 
     * @return the string content as a Content object
     */
    public Content parseContent(String content) throws InvalidGamaException {
        if (content.length() == 0) {
            return null;
        } else if (content.startsWith("=")) {
            return parseContentExpression(content);
        } else {
            return parseContentLiteral(content);
        }
    }

    /**
     * 
     * @return the string content as a Function or Reference object
     */
    public Content parseContentExpression(String content) throws InvalidGamaException {
        if (content.contains("(")) {
            return parseContentFunction(content);
        } else {
            return parseContentReference(content.substring(1));
        }
    }

    /**
     * 
     * @return the string content as a Function object
     */
    public Content parseContentFunction(String content) throws InvalidGamaException {
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
    public Content parseArgumentContent(String content) throws InvalidGamaException {
        if (content.length() == 0) {
            return null; // TODO: argumento vazio - exception??
        } else if (content.contains(";") && !content.startsWith("'")) {
            return parseContentReference(content);
        } else {
            return parseContentLiteral(content);
        }
    }

    /**
     * 
     * @return the string content as a Reference object
     */
    public Content parseContentReference(String content) throws InvalidGamaException {
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
