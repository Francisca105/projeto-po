package xxl.content.functions.interval.spaces;

import xxl.content.Content;
import xxl.content.literals.Literal;

/**
 * Class representing the coalesce function.
 */
public class Coal extends SpacesFunction {

    /**
     * Contructor.
     * 
     * @param arg1
     * @param arg2
     */
    public Coal(Content arg1, Content arg2) {
        super(arg1, arg2);
        setName("COALESCE");
    }

    /**
     * @see xxl.content.Content#value()
     */
    public Literal value() {
        return null; // TODO: implemet
    };
}