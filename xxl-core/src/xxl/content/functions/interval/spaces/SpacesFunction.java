package xxl.content.functions.interval.spaces;

import xxl.content.functions.interval.IntervalFunction;
import xxl.content.literals.Literal;
import xxl.content.Content;

public abstract class SpacesFunction extends IntervalFunction {

    /**
     * Constructor
     * 
     * @param arg1
     * @param arg2
     */

    public SpacesFunction(Content arg1, Content arg2) {
        super(arg1, arg2);
    }

    /**
     * @see xxl.content.Content#value()
     */ 
    public abstract Literal value();
}