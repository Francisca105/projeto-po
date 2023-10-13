package xxl.datastructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import xxl.Gamma;
import xxl.Address;
import xxl.Range;
import xxl.Cell;
import xxl.content.Content;
import xxl.exceptions.InvalidGamaException;
import xxl.utils.AddressComparator;

/**
 * Class representing a concrete implementation of the data structure.
 */
public class CellsMap extends DataStructure {

    /** Data structure (TreeMap). */
    private Map<Address, Cell> _dataStructure = new TreeMap<Address, Cell>(new AddressComparator());

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
     * 
     * @param address
     * @return the cell at the given address
     */
    public Cell getCell(Address address) throws InvalidGamaException {
        if (!_dataStructure.containsKey(address))
            throw new InvalidGamaException(address.toString());
        return _dataStructure.get(address);
    }

    /**
     * 
     * @param range
     * @return an array of the cells in the given range
     */
    public Cell[] getCells(Range range) throws InvalidGamaException {
        Address[] addresses = range.getAddresses();
        Cell[] cells = new Cell[addresses.length];
        int i = 0;

        for (Address a : addresses) {
            cells[i++] = getCell(a);
        }
        return cells;
    }

    /**
     * Sets the content of the cell at the given address.
     * 
     * @param address
     * @param content
     */
    public void setContentCell(Address address, Content content) throws InvalidGamaException{
        Cell cell = getCell(address);
        cell.setContent(content);
        _dataStructure.put(address, cell);
    }

    /*
     * @see xxl.datastructure.DataStructure#showRange(String)
     */
    public Collection<String> showRange(String range) throws InvalidGamaException {
        Gamma gamma = new Gamma(range);
        Collection<String> result = new ArrayList<String>();

        for (Address address : gamma.getAddresses()) {
            Cell cell = _dataStructure.get(address);
            if (cell == null)
                throw new InvalidGamaException(range);

            Content content = cell.getContent();
            
            if(content != null) 
                result.add(address.toString() + "|" + cell.getContent().showValue());
            else
                result.add(address.toString() + "|");
        }
        return result;
    }
}