package xxl.ds;

import xxl.Cell;

/**
 * Abstract class representing a data structure for the spreadsheet.
 */
public abstract class DS {

    /**
     * Returns the cell at the given coordinates.
     * 
     * @param coords of the cell
     * @return the cell at the given coordinates
     */
    public abstract Cell getCell(String coords);

    /**
     * Returns the cells in the given range.
     * 
     * @param coords of the first cell
     * @param coords2 of the last cell
     * @return the cells in the given range
     */
    public abstract Cell[] getCells(String coords, String coords2);
}