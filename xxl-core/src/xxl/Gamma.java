package xxl;

import xxl.content.functions.binary.Add;
import xxl.datastructure.DataStructure;
import xxl.exceptions.InvalidGammaException;

/**
 * Class representing a gamma.
 */
public class Gamma {

    /** This gamma is a range. */
    private Range _range;

    /** This gamma is an address. */
    private Address _address;

    /**
     * Constructor.
     * 
     * @param range
     * @throws InvalidGamaException
     */
    public Gamma (String range) throws InvalidGammaException {
        if(range.contains(":")) {
            _range = new Range(range);
        } else {
            _address = new Address(range);
        }
    }

    /**
     * This gamma is a solo address.
     *
     * @return the address
     */
    public Address getAddress() {
        return _address;
    }

    /**
     * This gamma is a range of addresses.
     *
     * @return the range of addresses
     */
    public Range getRange() {
        return _range;
    }

    /**
     * 
     * @return an array of addresses that compose this gamma
     */
    public Address[] getAddresses() {
        if (_range != null)
            return _range.getAddresses();
        else
            return new Address[] {_address};
    }

    public boolean isColumn() {
        Address[] addresses = getAddresses();
        if (addresses.length > 1)
            return addresses[0].getColumn() == addresses[1].getColumn();
        else
            return false;
    }

    public boolean isValid(DataStructure dataStructure) {
        Address[] addresses = getAddresses();
        int rows = dataStructure.getRows();
        int columns = dataStructure.getColumns();
        int length = addresses.length;
        if (addresses[0].getRow() > rows || addresses[0].getColumn() > columns || addresses[length-1].getRow() > rows || addresses[length-1].getColumn() > columns)
            return false;
        return true;
    }
}