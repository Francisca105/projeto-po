package xxl.content.functions.interval.nospaces;

import xxl.content.Content;
import xxl.content.literals.Literal;

/**
 * Class representing the average function.
 */
public class Avg extends NoSpacesFunction {

    /**
     * Constructor
     * 
     * @param arg1
     * @param arg2
     */
    public Avg(Content arg1, Content arg2) {
        super(arg1, arg2);
        setName("AVG");
    }

    /**
     * @see xxl.content.functions.Function#value()
     */
    public Literal value() {
        return null; // TODO: implemet
    };
}