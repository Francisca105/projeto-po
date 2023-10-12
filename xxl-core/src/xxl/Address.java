package xxl;

public class Address {
    
    /** Row */
    private int _row;

    /** Column */
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
}