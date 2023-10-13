package xxl;

import xxl.exceptions.InvalidGamaException;

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
    public Gamma (String range) throws InvalidGamaException {
        if(range.contains(":")) {
            _range = new Range(range);
        } else {
            _address = new Address(range);
        }
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