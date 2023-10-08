package xxl.content.functions.interval.nospaces;

import xxl.Cell;
import xxl.content.Content;
import xxl.content.functions.interval.IntervalF;

public abstract class NoSpacesF extends IntervalF {
    public NoSpacesF(Content arg1, Content arg2) {
        super(arg1, arg2);
    }
    
    public boolean checkArgument(Cell arg) {return false;} // FIXME

    public boolean hasValidArguments() { return true;} // FIXME

}