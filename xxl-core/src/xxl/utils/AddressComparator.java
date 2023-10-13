package xxl.utils;

import xxl.Address;

import java.io.Serializable;
import java.util.Comparator;

public class AddressComparator implements Comparator<Address>, Serializable {

    public int compare(Address a, Address o) {
        int comparedRows = compareRow(a, o);
        if (comparedRows != 0)
            return comparedRows;
        return compareColumn(a, o);
    }

    public int compareRow(Address a, Address o) {
        return Integer.compare(a.getRow(), o.getRow());
    }

    public int compareColumn(Address a, Address o) {
        return Integer.compare(a.getColumn(), o.getColumn());
    }
}