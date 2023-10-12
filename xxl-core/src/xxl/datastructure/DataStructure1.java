package xxl.datastructure;

import java.util.Map;
import java.util.TreeMap;

import xxl.Address;
import xxl.Cell;

public class DataStructure1 extends DataStructure {
    
    private Map<Address, Cell> _dataStructure = new TreeMap<Address, Cell>();

    /**
     * Constructor.
     * 
     * @param rows
     * @param columns
     */
    public DataStructure1(int rows, int columns) {
        _rows = rows;
        _columns = columns;
    }

    public DataStructure1(int rows, int columns) {
        super(rows, columns);
    }
}