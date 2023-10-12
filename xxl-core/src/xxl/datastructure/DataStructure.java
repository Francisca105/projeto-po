package xxl.datastructure;

public abstract class DataStructure {

    /** Number of the data structure rows. */
    private int _rows;

    /** Number of the data structure columns. */
    private int _columns;

    /**
     * Constructor.
     * 
     * @param rows
     * @param columns
     */
    public DataStructure(int rows, int columns) {
        _rows = rows;
        _columns = columns;
    }
}