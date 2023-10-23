package xxl.datastructure;

import java.io.Serializable;

import xxl.Range;
import xxl.Address;
import xxl.Cell;
import xxl.content.Content;
import xxl.exceptions.InvalidGammaException;
import xxl.visits.CellVisitor;

/**
 * Class representing a data structure.
 */
public abstract class DataStructure implements Serializable {

    /** Number of the data structure rows. */
    private int _rows;

    /** Number of the data structure columns. */
    private int _columns;

    /**
     * 
     * @return the number of the data structure rows
     */
    public int getRows() {
        return _rows;
    }

    /**
     * Sets the number of the data structure rows.
     * 
     * @param rows
     */
    public void setRows(int rows) {
        _rows = rows;
    }

    /**
     * 
     * @return the number of the data structure columns
     */
    public int getColumns() {
        return _columns;
    }

    /**
     * Sets the number of the data structure columns.
     * 
     * @param rows
     */
    public void setColumns(int columns) {
        _columns = columns;
    }

    /**
     * Receives a range and returns the Strings to show to the user.
     * 
     * @param range
     * @throws InvalidGamaException
     */
    public abstract void showRange(CellVisitor visitor, String range) throws InvalidGammaException;

    /**
     * 
     * @param address
     * @return the cell at the given address
     * @throws InvalidGamaException
     */
    public abstract Cell getCell(Address address) throws InvalidGammaException;

    public abstract Cell[] getCells(Range range) throws InvalidGammaException;

    public abstract void setContentCell(Address address, Content content) throws InvalidGammaException;
}