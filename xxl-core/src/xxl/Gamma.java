package xxl;

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
        if(_range != null)
            return _range.getAddresses();
        else
            return new Address[] {_address};
    }
}