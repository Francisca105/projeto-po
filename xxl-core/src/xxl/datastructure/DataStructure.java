package xxl.datastructure;

import java.io.Serializable;
import java.util.Map;

import xxl.Range;
import xxl.Address;
import xxl.Cell;
import xxl.exceptions.InvalidGammaException;

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
     * 
     * @param address
     * @return the cell at the given address
     * @throws InvalidGamaException
     */
    public abstract Cell getCell(Address address) throws InvalidGammaException;

    public abstract Map<Address, Cell> getAllCells();

    public abstract Cell[] getCells(Range range) throws InvalidGammaException;

}