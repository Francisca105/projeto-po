package xxl.content.functions.interval.spaces;

import xxl.content.functions.interval.IntervalFunction;
import xxl.content.literals.Literal;
import xxl.datastructure.DataStructure;
import xxl.exceptions.InvalidGammaException;
import xxl.Range;

/**
 * Abstract class representing a non binary function that can have spaces.
 */
public abstract class SpacesFunction extends IntervalFunction {

    /**
     * Constructor
     * 
     * @param range
     */
    public SpacesFunction(Range range, DataStructure ds) throws InvalidGammaException {
        super(range, ds);
    }

    /**
     * @see xxl.content.Content#value()
     */ 
    public abstract Literal value();

//    public boolean checkArgument(Range arg) {
//        if (arg == null)
//            return false;
//        return true;
//    }

    @Override
    public String toString() {
        return super.toString();
    }
}