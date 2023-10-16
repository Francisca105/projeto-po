package xxl.content.functions.interval.spaces;

import xxl.content.Content;
import xxl.content.literals.Literal;

/**
 * Class representing the concatenate function.
 */
public class Conc extends SpacesFunction {

    /**
     * Constructor
     * 
     * @param arg1
     * @param arg2
     */
    public Conc(Content arg1, Content arg2) {
        super(arg1, arg2);
        setName("CONCAT");
    }

    /**
     * @see xxl.content.Content#value()
     */
    public Literal value() {
        return null; // TODO: implemet
    };
}