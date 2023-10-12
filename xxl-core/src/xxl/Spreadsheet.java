package xxl;
// FIXME import classes

import java.io.Serial;
import java.io.Serializable;

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

    /** Number of the spreadsheet rows. */
    private int _rows;

    /** Number of the spreadsheet columns. */
    private int _columns;

    /**
     * Insert specified content in specified range.
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    public void insertContents(String rangeSpecification, String contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
        //FIXME implement method
    }

}
