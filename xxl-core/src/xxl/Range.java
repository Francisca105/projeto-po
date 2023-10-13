package xxl;

import java.util.List;
import java.util.ArrayList;

import xxl.exceptions.InvalidGamaException;

/**
 * Class representing a range of addresses.
 */
public class Range {
    private List<Address> _addresses = new ArrayList<Address>();

    /**
     * Constructor
     * 
     * @param start
     * @param end
     */
    public Range(Address start, Address end) throws InvalidGamaException {
        if(!isValid(start, end))
            throw new InvalidGamaException(start.toString() + ":" + end.toString());  

        if(isInverse(start, end)) {
            insertAddresses(end, start);
        }

        insertAddresses(start, end);
    }


    /**
     * Constructor
     * 
     * @param range
     */
    public Range(String range) throws InvalidGamaException {
        String[] parts = range.split(":");

        if(parts.length != 2)
            throw new InvalidGamaException(range);

        try {
            Address start = new Address(parts[0]);
            Address end = new Address(parts[1]);
            if(!isValid(start, end))
                throw new InvalidGamaException(range);

            if(isInverse(start, end)) {
                insertAddresses(end, start);
            }

            insertAddresses(start, end);
        } catch (InvalidGamaException e) {
            throw new InvalidGamaException(range);
        }
    }

    public boolean isInverse(Address start, Address end) {
        if(start.getRow() > end.getRow() || 
            start.getRow() == end.getRow() && start.getColumn() > end.getColumn())
            return true;
        return false;
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
