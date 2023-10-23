package xxl.exceptions;

/**
 * Exception thrown when an invalid address is used or tried to be created.
 */
public class ParseFunctionException extends Exception{

    /** Invalid gamma. */
    private String _name;

    /**
     * Constructor.
     * 
     * @param gamma
     */
    public ParseFunctionException(String name) {
        _name = name;
    }

    /**
     * 
     * @return the invalid gamma
     */
    public String getName() {
        return _name;
    }
}
