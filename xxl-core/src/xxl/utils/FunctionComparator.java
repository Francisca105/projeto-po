package xxl.utils;

import java.io.Serializable;
import java.util.Comparator;

import xxl.Address;
import xxl.exceptions.InvalidGammaException;

/**
 * Class representing an function comparator.
 */
public class FunctionComparator implements Comparator<String>, Serializable {

    /**
     * Compares two addresses to determine their order.
     * The order is determined by the function name and then by the address.
     * 
     * @param a first address
     * @param b second address
     */
    public int compare(String a, String b) {
        String fNameA = getFunctionName(a);
        String fNameB = getFunctionName(b);
        int comparedFunctionNames = fNameA.compareTo(fNameB);
        
        if (comparedFunctionNames != 0)
            return comparedFunctionNames;
        
        String addressA = a.split("\\|")[0];
        String addressB = b.split("\\|")[0];

        int comparedAddresses = addressA.compareTo(addressB);

        try {
            Address A = new Address(addressA);
            Address B = new Address(addressB);

            comparedAddresses = new AddressComparator().compare(A, B);
        } catch (InvalidGammaException e) {
            // no need to handle this exception
        }
        return comparedAddresses;   
    }

    /**
     * 
     * @param function
     * @return the function name
     */
    public String getFunctionName(String function) {
        String split = function.split("\\|")[1];
        return split.substring(split.indexOf("="), split.indexOf("("));
    }
}