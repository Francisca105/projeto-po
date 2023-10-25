package xxl;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import xxl.exceptions.InvalidGammaException;

/**
 * Class representing a range of addresses.
 */
public class Range implements Serializable {
    
    /** List of the address composing this range. */
    private List<Address> _addresses = new ArrayList<Address>();

    /**
     * Constructor
     * 
     * @param start
     * @param end
     */
    public Range(Address start, Address end) throws InvalidGammaException {
        if(!isValid(start, end))
            throw new InvalidGammaException(start.toString() + ":" + end.toString());  
        insertAddresses(start, end);
    }

    /**
     * Constructor
     * 
     * @param range
     */
    public Range(String range) throws InvalidGammaException {
        String[] parts = range.split(":");

        if(parts.length != 2)
            throw new InvalidGammaException(range);

        try {
            Address start = new Address(parts[0]);
            Address end = new Address(parts[1]);
            if(!isValid(start, end))
                throw new InvalidGammaException(range);
            insertAddresses(start, end);
        } catch (InvalidGammaException e) {
            throw new InvalidGammaException(range);
        }
    }

    /**
     * 
     * @return the list of addresses
     */
    public Address[] getAddresses() {
        Address[] addresses = new Address[_addresses.size()];
        int i = 0;
        for (Address address : _addresses)
            addresses[i++] = address;
        return addresses;
    }

    /**
     * 
     * @return the start address
     */
    public Address getStart() {
        return _addresses.size() > 0 ? _addresses.get(0) : null;
    }

    /**
     * 
     * @return the end address
     */
    public Address getEnd() {
        return _addresses.size() > 0 ? _addresses.get(_addresses.size() - 1) : null;
    }

    /**
     * Inserts the addresses from the range in the list.
     * 
     * @param start
     * @param end
     */
    public void insertAddresses(Address start, Address end) {
        if(start.getRow() == end.getRow()) {
            for(int i = start.getColumn(); i <= end.getColumn(); i++) {
                _addresses.add(new Address(start.getRow(), i));
            }
        } else {
            for(int i = start.getRow(); i <= end.getRow(); i++) {
                _addresses.add(new Address(i, start.getColumn()));
            }
        }
    }

    /**
     * Checks whether the range is valid.
     * 
     * @return true if the range is valid, false otherwise
     */
    public boolean isValid(Address start, Address end) {
        return start.getRow() == end.getRow() || start.getColumn() == end.getColumn(); 
    }

    @Override
    public String toString() {
        return getStart().toString() + ":" + getEnd().toString();
    }
}

//    /**
//     * 
//     * @param start
//     * @param end
//     * @return true if the range is inversed, false otherwise
//     */
//    public boolean isInversed(Address start, Address end) {
//        if(start.getRow() > end.getRow() || 
//            start.getRow() == end.getRow() && start.getColumn() > end.getColumn())
//            return true;
//        return false;
//    }