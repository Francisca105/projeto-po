package xxl.utils;

import java.io.Serializable;
import java.util.Comparator;

import xxl.Address;

/**
 * Class representing an address comparator.
 */
public class AddressComparator implements Comparator<Address>, Serializable {

    /**
     * Compares two addresses to determine their order.
     * 
     * @param a first address
     * @param b second address
     */
    public int compare(Address a, Address b) {
        int comparedRows = compareRow(a, b);
        if (comparedRows != 0)
            return comparedRows;
        return compareColumn(a, b);
    }

    /**
     * Compares two addresses rows.
     * 
     * @param a first address
     * @param b second address
     */
    public int compareRow(Address a, Address o) {
        return Integer.compare(a.getRow(), o.getRow());
    }

    /**
     * Compares two addresses columns.
     * 
     * @param a first address
     * @param b second address
     */
    public int compareColumn(Address a, Address o) {
        return Integer.compare(a.getColumn(), o.getColumn());
    }
}