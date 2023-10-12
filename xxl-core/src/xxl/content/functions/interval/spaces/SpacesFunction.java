package xxl.content.functions.interval.spaces;

import xxl.content.functions.interval.IntervalFunction;
import xxl.content.Content;

public abstract class SpacesFunction extends IntervalFunction {

    public SpacesFunction(Content arg1, Content arg2) {
        super(arg1, arg2);
    }
    
    public abstract Literal value();
}