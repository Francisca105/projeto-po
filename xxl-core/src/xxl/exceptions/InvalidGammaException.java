package xxl.exceptions;

/**
 * Exception thrown when an invalid address is used or tried to be created.
 */
public class InvalidGammaException extends Exception{

    /** Invalid gamma. */
    private String _gamma;

    /**
     * Constructor.
     * 
     * @param gamma
     */
    public InvalidGammaException(String gamma) {
        _gamma = gamma;
    }

    /**
     * 
     * @return the invalid gamma
     */
    public String getAddress() {
        return _gamma;
    }
}
