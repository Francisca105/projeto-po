package xxl.content.functions.interval.nospaces;

import xxl.content.Content;
import xxl.content.functions.interval.IntervalFunction;
import xxl.content.literals.Literal;

public abstract class NoSpacesFunction extends IntervalFunction {
    /**
     * Constructor
     * 
     * @param arg1
     * @param arg2
     */
    public NoSpacesFunction(Content arg1, Content arg2) {
        super(arg1, arg2);
    }

    public abstract Literal value();
}