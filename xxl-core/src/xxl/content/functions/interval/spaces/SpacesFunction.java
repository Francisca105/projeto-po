package xxl.content.functions.interval.spaces;

import xxl.content.functions.interval.IntervalFunction;
import xxl.content.literals.Literal;

import xxl.content.Reference;
import xxl.Range;
import xxl.content.Content;

/**
 * Abstract class representing a non binary function that can have spaces.
 */
public abstract class SpacesFunction extends IntervalFunction {
    private Range _range;
    /**
     * Constructor
     * 
     * @param arg1
     * @param arg2
     */
    public SpacesFunction(Content arg1, Content arg2) {
        super(arg1, arg2);
        // _range = new Range();
    }

    /**
     * @see xxl.content.Content#value()
     */ 
    public abstract Literal value();
}