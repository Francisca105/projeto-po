package xxl.datastructure;

public abstract class DataStructure {

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

    public void setRows(int rows) {
        _rows = rows;
    }
}