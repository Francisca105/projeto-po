package xxl.content.functions.interval.nospaces;

import xxl.content.Content;
import xxl.content.literals.Literal;

/**
 * Class representing the product function.
 */
public class Prod extends NoSpacesFunction {

    /**
     * Constructor
     * 
     * @param arg1
     * @param arg2
     */
    public Prod(Content arg1, Content arg2) {
        super(arg1, arg2);
        setName("PRODUCT");
    }

    /**
     * @see xxl.content.functions.Function#value()
     */
    public Literal value() {
        return null; // TODO implement
    }
}