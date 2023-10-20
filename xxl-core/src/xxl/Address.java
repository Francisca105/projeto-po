package xxl;

import java.io.Serializable;

import xxl.exceptions.InvalidGammaException;

/**
 * Class representing an address.
 */
public class Address implements Serializable {
    
    /** Address row. */
    private int _row;

    /** Address column. */
    private int _column;

    /**
     * Constructor.
     * 
     * @param row
     * @param column
     */
    public Address(int row, int column) {
        _row = row;
        _column = column;
    }

    /**
     * Constructor.
     * 
     * @param add
     */
    public Address(String add) throws InvalidGammaException {
        String[] parts = add.split(";");

        if(parts.length != 2)
            throw new InvalidGammaException(add);

        try {
            _row = Integer.parseInt(parts[0]);
            _column = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    /**
     * 
     * @return the row
     */
    public int getRow() {
        return _row;
    }

    /**
     * 
     * @return the column
     */
    public int getColumn() {
        return _column;
    }

    @Override
    public String toString() {
        return getRow() + ";" + getColumn();
    }
}