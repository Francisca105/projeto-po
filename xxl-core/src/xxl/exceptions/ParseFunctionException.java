package xxl.exceptions;

/**
 * Exception thrown when an when an unknown function is tried to be parsed.
 */
public class ParseFunctionException extends Exception{

    /** Invalid name */
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
     * @return the invalid name
     */
    public String getName() {
        return _name;
    }
}