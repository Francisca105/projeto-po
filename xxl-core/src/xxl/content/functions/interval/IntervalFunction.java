package xxl.content.functions.interval;

import java.util.ArrayList;
import java.util.List;

import xxl.Cell;
import xxl.Range;
import xxl.content.functions.Function;
import xxl.content.literals.Literal;
import xxl.datastructure.DataStructure;
import xxl.exceptions.InvalidGammaException;

/**
 * Abstract class representing a non binary function..
 */
public abstract class IntervalFunction extends Function {

    /** The range of the function */
    private Range _range;

    private Cell[] _cells;

    /**
     * Constructor
     * 
     * @param range
     */
    public IntervalFunction(Range range, DataStructure ds) throws InvalidGammaException {
        _range = range;
        _cells = ds.getCells(range);    
    }

    /**
     * 
     * @return the range
     */
    public Range getRange() {
        return _range;
    }

    public Cell[] getCells() {
        return _cells;
    }

    /**
     * @see xxl.content.Content#value()
     */
    public abstract Literal value();

    @Override
    public String toString() {
        return "(" + getRange().toString() + ")";
    }
}