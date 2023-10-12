package xxl.utils;

import xxl.Address;

import java.util.Comparator;

public class AddressComparator implements Comparator<Address> {

    public int compare(Address a, Address o) {
        int comparedRows = Integer.compare(a.getRow(), o.getRow());
        if (comparedRows != 0)
            return comparedRows;
        return Integer.compare(a.getColumn(), o.getColumn());
    }
}