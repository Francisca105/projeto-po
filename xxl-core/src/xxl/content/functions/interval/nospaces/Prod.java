package xxl.content.functions.interval.nospaces;

import xxl.Cell;
import xxl.Range;
import xxl.content.literals.Int;
import xxl.content.literals.InvalidValue;
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
        setName("PRODUCT");
    }

    /**
     * @see xxl.content.functions.Function#value()
     */
    public Literal value() {
        try {
            int prod = 1;
            Cell[] cells = getCells();
            for (Cell cell : cells) {
                if (cell.value() == null)
                    return new InvalidValue();
                prod *= ((Int)cell.value()).getValue();
            }
            return new Int(prod); 
        } catch (ClassCastException e) {
            return new InvalidValue();
        }
    }

    public void accept(CellVisitor visitor) {
        visitor.visitProdFunction(this);
    }

    @Override
    public String toString() {
        return "PRODUCT(" + getRange() + ")";
    }

    public String string() {
        return "=" + toString();
    }
}