package xxl.datastructure;

import java.util.Map;
import java.util.TreeMap;

import xxl.Address;
import xxl.Range;
import xxl.Cell;
import xxl.exceptions.InvalidGammaException;
import xxl.utils.AddressComparator;

/**
 * Class representing a concrete implementation of the data structure.
 */
public class CellsMap extends DataStructure {

    /** Data structure (TreeMap). */
    private Map<Address, Cell> _dataStructure = new TreeMap<Address, Cell>(new AddressComparator());
    
    /**
     * Constructor.
     */
    public CellsMap() {
        // default constructor
    }
    
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
            for (int j = 1; j <= columns; j++) {
                Address address = new Address(i, j);
                Cell cell = new Cell();
                _dataStructure.put(address, cell);
            }
        }
    }

    /**
     * @see xxl.datastructure.DataStructure#getAllCells()
     */
    public Map<Address,Cell>  getAllCells() {
        return _dataStructure;
    }

    /**
     * @see xxl.datastructure.DataStructure#getCell(xxl.Address)
     */
    public Cell getCell(Address address) throws InvalidGammaException {
        if (!_dataStructure.containsKey(address))
            throw new InvalidGammaException(address.toString());
        return _dataStructure.get(address);
    }

    /**
     * @see xxl.datastructure.DataStructure#getCells(xxl.Range)
     */
    public Cell[] getCells(Range range) throws InvalidGammaException {
        Address[] addresses = range.getAddresses();
        Cell[] cells = new Cell[addresses.length];
        int i = 0;

        for (Address a : addresses)
            cells[i++] = getCell(a);
        return cells;
    }
}