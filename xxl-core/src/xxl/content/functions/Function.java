package xxl.content.functions;

import xxl.content.Content;
import xxl.content.literals.Literal;

/**
 * Abstract class representing a function.
 */
public abstract class Function extends Content {

    /** First argument */
    private Content _arg1;

    /** Secound argument */
    private Content _arg2;

    /** The name of the function */
    private String _name;

    /**
     * Defines the name of the function
     * 
     * @return
     */
    public void setName(String name) {
        _name = name;
    }

    /**
     * Gets the name of the function
     * @return string
     */
    public String getName() {
        return _name;
    }

    /**
     * @see xxl.content.Content#value()
     */
    public abstract Literal value();
}