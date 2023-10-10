package xxl;

import xxl.content.Content;
// FIXME import classes
import xxl.ds.DS;
import xxl.ds.DS1;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.utils.Parser;
import xxl.utils.Position;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;

    // private String _name;

    /** Number of rows of the spreadsheet. */
    private int _nRows;

    /** Number of columns of the spreadsheet. */
    private int _nColumns;

    /** Data structure for the spreadsheet. */
    private DS _ds;

    /** Data structure for the cut buffer. */
    private DS _cutBuffer; // TODO

    private Parser _parser = new Parser();

    public Spreadsheet() {
    }

    public Spreadsheet(int nRows, int nColumns) {
        _ds = new DS1(nRows, nColumns);
        setRows(nRows);
        setColumns(nColumns);
    }

    public void setDS(DS ds) {
        _ds = ds;
    }

    /**
     * 
     * @return the number of rows of the spreadsheet.
     */
    public int getNRows() {
        return _nRows;
    }

    /**
     * Sets the number of rows of the spreadsheet.
     * 
     * @param nRows
     */
    public void setRows(int nRows) {
        _nRows = nRows;
    }

    /**
     * 
     * @return the number of columns of the spreadsheet.
     */
    public int getNColumns() {
        return _nColumns;
    }

    /**
     * Sets the number of columns of the spreadsheet.
     * 
     * @param nColumns
     */
    public void setColumns(int nColumns) {
        _nColumns = nColumns;
    }

    /**
     * Insert specified content in specified range.
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    public void insertContents(String rangeSpecification, String contentSpecification)
            throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
        Position[] pos = _parser.parseRange(rangeSpecification);
        for (Position p : pos) {
            Cell cell = _ds.getCell(p);
            Content content = _parser.parseContent(contentSpecification);
            if (content != null)
                cell.setContent(content);
        }
    }
}
