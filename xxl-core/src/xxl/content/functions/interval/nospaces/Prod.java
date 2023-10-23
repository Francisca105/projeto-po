package xxl.content.functions.interval.nospaces;

import xxl.Range;
import xxl.content.literals.Literal;
import xxl.datastructure.DataStructure;
import xxl.exceptions.InvalidGammaException;
import xxl.visits.CellVisitor;

/**
 * Class representing the product function.
 */
public class Prod extends NoSpacesFunction {

    /**
     * Contructor.
     * 
     * @param range
     * @param ds
     */
    public Prod(Range range, DataStructure ds) throws InvalidGammaException {
        super(range, ds);
        setName("COALESCE");
    }

    /**
     * @see xxl.content.functions.Function#value()
     */
    public Literal value() {
        return null; // TODO implement
    }

    public void accept(CellVisitor visitor) {
        
    }
}