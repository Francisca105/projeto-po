package xxl;

import java.util.List;
import java.util.ArrayList;

import xxl.exceptions.InvalidGamaException;

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

            insertAddresses(start, end);
        } catch (InvalidGamaException e) {
            throw new InvalidGamaException(range);
        }
    }

    /**
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
     * @return the addresses
     */
    public Address[] getAddresses() {
        Address[] addresses = new Address[_addresses.size()];
        return addresses;
    }

    /**
     * 
     * @return the start address
     */
    public Address getStart() {
        return _addresses.get(0);
    }

    /**
     * 
     * @return the end address
     */
    public Address getEnd() {
        return _addresses.get(_addresses.size() - 1);
    }

    /**
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
