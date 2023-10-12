package xxl.datastructure;

import java.util.Map;
import java.util.TreeMap;

import xxl.Address;
import xxl.Range;
import xxl.Cell;

public class CellsMap extends DataStructure {

    private Map<Address, Cell> _dataStructure = new TreeMap<Address, Cell>();

    /**
     * Constructor.
     * 
     * @param rows
     * @param columns
     */
    public CellsMap(int rows, int columns) {
        setRows(rows);
        setColumns(columns);
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; i++) {
                Address address = new Address(i, j);
                Cell cell = new Cell();
                _dataStructure.put(address, cell);
            }
        }
    }

    /**
     * 
     * @param address
     * @return the cell at the given address
     */
    public Cell getCell(Address address) {
        return _dataStructure.get(address);
    }


    /**
     * 
     * @param range
     * @return the cells in the given range
     */
    public Cell[] getCells(Range range) {
        Address[] addresses = range.getAddresses();
        Cell[] cells = new Cell[addresses.length];
        int i = 0;

        for (Address a : addresses) {
            cells[i++] = getCell(a);
        }

        return cells;
    }

}