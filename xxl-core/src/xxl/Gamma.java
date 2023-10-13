package xxl;

import xxl.Address;
import xxl.Range;
import xxl.exceptions.InvalidGamaException;

public class Gamma {
    private Range _range;
    private Address _address;
// ei, o que achas de antes de te meteres em loucuras, dar-mos commit disto no gitlab? ok, vou apagar entao coisas random
    public Gamma (String range) throws InvalidGamaException {
        if(range.contains(":")) {
            _range = new Range(range);
            System.out.println(range);
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