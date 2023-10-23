package xxl.content.functions.interval.nospaces;

import xxl.Cell;
import xxl.Range;
import xxl.content.functions.interval.IntervalFunction;
import xxl.content.literals.Int;
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

//    public boolean checkArgument(Range arg) {
//        try {
//            Cell[] cells = getCells();
//            for (Cell cell : cells) {
//                if (cell.value() == null)
//                    return false;
//                Int i = (Int)cell.value();
//            }
//            return true;
//        } catch (ClassCastException e) {
//            return false;
//        }
//    }

    @Override
    public String toString() {
        return super.toString();
    }
}