package xxl.ds;

import xxl.Cell;
import xxl.utils.Parser;
import xxl.utils.Position;

/**
 * Abstract class representing a data structure for the spreadsheet.
 */
public abstract class DS {

    /** Number of rows of the ds. */
    private int _nRows;

    /** Number of columns of the ds. */
    private int _nColumns;

    private Parser _parser = new Parser();

    /**
     * 
     * @return the parser
     */
    public Parser getParser() {
        return _parser;
    }

    /**
     * 
     * @return the number of rows of the current ds
     */
    public int getNRows() {
        return _nRows;
    }

    /**
     * Sets the number of rows of the ds.
     * 
     * @param nRows
     */
    public void setRows(int nRows) {
        _nRows = nRows;
    }

    /**
     * 
     * @return the number of columns of the current ds
     */
    public int getNColumns() {
        return _nColumns;
    }

    /**
     * Sets the number of columns of the ds.
     * 
     * @param nColumns
     */
    public void setColumns(int nColumns) {
        _nColumns = nColumns;
    }

    /**
     * 
     * @param coords of the cell
     * @return the cell at the given coordinates
     */
    public abstract Cell getCell(String coords);

    /**
     * 
     * @param coords of the cell
     * @return the cell at the given coordinates
     */
    public abstract Cell getCell(Position coords);

    /**
     * 
     * @param coords  of the first cell
     * @param coords2 of the last cell
     * @return the cells in the given range
     */
    //public abstract Cell[] getCells(String coords, String coords2);
}