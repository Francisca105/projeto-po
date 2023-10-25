package xxl.content.functions;

import xxl.content.Content;
import xxl.content.literals.Literal;

/**
 * Abstract class representing a function.
 */
public abstract class Function extends Content {

    /** The name of the function */
    private String _name;

    /**
     * Sets the name of the function
     * 
     * @param name
     */
    public void setName(String name) {
        _name = name;
    }

    /**
     *
     * @return the name of the function
     */
    public String getName() {
        return _name;
    }

    /**
     * @see xxl.content.Content#value()
     */
    public abstract Literal value();
}