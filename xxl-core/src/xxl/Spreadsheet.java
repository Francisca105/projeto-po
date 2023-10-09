package xxl;
// FIXME import classes
import xxl.ds.DS;
import xxl.exceptions.UnrecognizedEntryException;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;

    /** Number of rows of the spreadsheet. */
    private int _nRows;

    /** Number of columns of the spreadsheet. */
    private int _nColumns;

    /** Data structure for the spreadsheet. */
    private DS _ds;

    /** Data structure for the cut buffer. */
    private DS _cutBuffer;

    public Spreadsheet() {
    }
    
    public Spreadsheet(int nRows, int nColumns) {
        _nRows = nRows;
        _nColumns = nColumns;
    }

    public void setRows(int nRows) {
        _nRows = nRows;
    }

    public void setColumns(int nColumns) {
        _nColumns = nColumns;
    }

    /**
     * 
     * @return the number of rows of the spreadsheet.
     */
    public int getNRows() {
        return _nRows;
    }

    /**
     * 
     * @return the number of columns of the spreadsheet.
     */
    public int getNColumns() {
        return _nColumns;
    }

    /**
     * Insert specified content in specified range.
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    public void insertContents(String rangeSpecification, String contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
        
        
    }

}
