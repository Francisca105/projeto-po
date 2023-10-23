package xxl.content.functions.interval.nospaces;

import xxl.Range;
import xxl.content.literals.InvalidValue;
import xxl.content.literals.Literal;
import xxl.datastructure.DataStructure;
import xxl.exceptions.InvalidGammaException;
import xxl.visits.CellVisitor;
import xxl.Cell;
import xxl.content.literals.Int;

/**
 * Class representing the average function.
 */
public class Avg extends NoSpacesFunction {

    /**
     * Contructor.
     * 
     * @param range
     * @param ds
     */
    public Avg(Range range, DataStructure ds) throws InvalidGammaException {
        super(range, ds);
        setName("AVERAGE");
    }

    /**
     * @see xxl.content.functions.Function#value()
     */
    public Literal value() {
        try {
            int sum = 0;
            int count = 0;
            Cell[] cells = getCells();
            for (Cell cell : cells) {
                if (cell.value() == null)
                    return new InvalidValue();
                sum += ((Int)cell.value()).getValue();
                count++;
            }
            return new Int(sum / count); 
        } catch (ClassCastException e) {
            return new InvalidValue();
        }
    };

    public void accept(CellVisitor visitor) {
        visitor.visitAvgFunction(this);
    }

    @Override
    public String toString() {
        return "AVERAGE" + super.toString();
    }

    public String string() {
        return "=" + toString();
    }
}