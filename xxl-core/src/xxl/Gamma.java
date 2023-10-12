package xxl;

import xxl.Address;
import xxl.Range;
import xxl.exceptions.InvalidGamaException;

public class Gamma {
    private Range _range;
    private Address _address;

    public Gamma (String range) throws InvalidGamaException {
        if(range.contains(":")) {
            _range = new Range(range);
        } else {
            _address = new Address(range);
        }
    }

    public Address[] getAddresses() {
        if(_range != null) {
            return _range.getAddresses();
        } else {
            return new Address[] {_address};
        }
    }
}