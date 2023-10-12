package xxl;
// FIXME import classes

import java.io.Serial;
import java.io.Serializable;

import xxl.datastructure.CellsMap;
import xxl.datastructure.DataStructure;
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
    private String _name;

    /** Number of the spreadsheet rows. */
    private int _rows;

    /** Number of the spreadsheet columns. */
    private int _columns;

    /** Data structure of the spreadsheet */
    private DataStructure _cells;

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
     * Insert specified content in specified range.
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    public void insertContents(String rangeSpecification, String contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
        try {
            Address address = new Address(rangeSpecification);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
