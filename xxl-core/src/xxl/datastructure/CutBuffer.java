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
import xxl.exceptions.InvalidGammaException;
import xxl.utils.AddressComparator;

/**
 * Class representing a concrete implementation of the data structure.
 */
public class CutBuffer extends DataStructure {

    /** Data structure (TreeMap). */
    private Map<Address, Cell> _dataStructure = new TreeMap<Address, Cell>(new AddressComparator());

    /**
     * Constructor.
     * 
     * @param rows
     * @param columns
     */
    public CutBuffer(/* TODO */) {
        
    }

    public Cell getCell(Address address) throws InvalidGammaException {
        if (!_dataStructure.containsKey(address))
            throw new InvalidGammaException(address.toString());
        return _dataStructure.get(address);
    }


    /**
     * @see xxl.datastructure.DataStructure#showRange(String)
     */
    public Collection<String> showRange(String s) throws InvalidGammaException {
        Collection<String> result = new ArrayList<String>();

        for (Address address : _dataStructure.keySet()) {
            Cell cell = getCell(address);
            Content content = cell.getContent();

            if(content != null) 
                result.add(address.toString() + "|" + content.showValue());
            else
                result.add(address.toString() + "|");
        }
        return result;
    }

    public void setContentCell(Address address, Content content) throws InvalidGammaException {
        
    }
}