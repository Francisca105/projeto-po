package xxl.datastructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import xxl.Address;
import xxl.Range;
import xxl.Cell;
import xxl.content.Content;
import xxl.exceptions.InvalidGamaException;
import xxl.utils.AddressComparator;

public class CellsMap extends DataStructure {

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

    /**
     * Sets the content of the cell at the given address.
     * 
     * @param address
     * @param content
     */
    public void setContentCell(Address address, Content content) {
        Cell cell = getCell(address); // TODO: nao sei se a referencia perde a celula
        cell.setContent(content);
        _dataStructure.put(address, cell);
    }

    
    public Collection<String> showRange(String range) throws InvalidGamaException {
        Range r = new Range(range);
        Collection<String> result = new ArrayList<String>();
        for (Address address : r.getAddresses()) {
            Cell cell = _dataStructure.get(address);
            result.add(cell.toString());
        }
        return result;
    }
}