package xxl.exceptions;

import xxl.Address;

/**
 * Exception thrown when an invalid address is used or tried to be created.
 */

public class InvalidGamaException extends Exception{
    private String _gama;

    public InvalidGamaException(String gama) {
        _gama = gama;
    }

    public String getAddress() {
        return _gama;
    }
}
