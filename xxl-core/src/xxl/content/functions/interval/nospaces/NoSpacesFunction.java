package xxl.content.functions.interval.nospaces;

import xxl.Range;
import xxl.content.functions.interval.IntervalFunction;
import xxl.content.literals.Literal;
import xxl.datastructure.DataStructure;
import xxl.exceptions.InvalidGammaException;

/**
 * Abstract class representing a non binary function that can't have spaces.
 */
public abstract class NoSpacesFunction extends IntervalFunction {
    
    /**
     * Constructor
     * 
     * @param range
     */
    public NoSpacesFunction(Range range, DataStructure ds) throws InvalidGammaException {
        super(range, ds);
    }

    /**
     * @see xxl.content.Content#value()
     */
    public abstract Literal value();

    @Override
    public String toString() {
        return super.toString();
    }
}