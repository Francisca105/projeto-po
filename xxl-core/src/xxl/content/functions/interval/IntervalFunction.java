package xxl.content.functions.interval;

import xxl.content.Content;
import xxl.content.functions.Function;
import xxl.content.literals.Literal;

/**
 * Abstract class representing a non binary function..
 */
public abstract class IntervalFunction extends Function {

    /**
     * Constructor
     * 
     * @param arg1
     * @param arg2
     */
    public IntervalFunction(Content arg1, Content arg2) {
        super(arg1, arg2);
    }

    /**
     * @see xxl.content.Content#value()
     */
    public abstract Literal value();
}