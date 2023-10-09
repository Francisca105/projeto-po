package xxl.ds;

import xxl.Cell;
import xxl.utils.Parser;
import xxl.utils.Position;

/**
 * Abstract class representing a data structure for the spreadsheet.
 */
public abstract class DS {

    /** Number of rows of the spreadsheet. */
    private int _nRows;

    /** Number of columns of the spreadsheet. */
    private int _nColumns;

    private Parser _parser = new Parser();

    public Parser getParser() {
        return _parser;
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

    public void setRows(int nRows) {
        _nRows = nRows;
    }

    public void setColumns(int nColumns) {
        _nColumns = nColumns;
    }

    /**
     * Returns the cell at the given coordinates.
     * 
     * @param coords of the cell
     * @return the cell at the given coordinates
     */
    public abstract Cell getCell(String coords);

    public abstract Cell getCell(Position coords);

    /**
     * Returns the cells in the given range.
     * 
     * @param coords  of the first cell
     * @param coords2 of the last cell
     * @return the cells in the given range
     */
    public abstract Cell[] getCells(String coords, String coords2);
}