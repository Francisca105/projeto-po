package xxl;
// FIXME import classes
import xxl.content.Content;
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

    /** Data structure of the spreadsheet. */
    private DS _ds;

    /** Data structure of the cut buffer. */
    private DS _cutBuffer; // TODO

    private Parser _parser = new Parser();

    public Spreadsheet() {
    }

    /**
     * Creates a spreadsheet with the specified number of rows and columns.
     * 
     * @param nRows
     * @param nColumns
     */
    public Spreadsheet(int nRows, int nColumns) {
        _ds = new DS1(nRows, nColumns);
        setRows(nRows);
        setColumns(nColumns);
    }

    /**
     * 
     * @return the data structure of the spreadsheet.
     */
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

        Position pos = _parser.parseRange(rangeSpecification);
        try {
            Cell cell = _ds.getCell(pos);
            Content content = _parser.parseContent(contentSpecification);

            if (content != null)
                cell.setContent(content);
            
        } catch (Exception e) {
            System.out.println(pos);
        }
    }
}
