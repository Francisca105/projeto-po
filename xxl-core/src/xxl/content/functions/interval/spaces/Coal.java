package xxl.content.functions.interval.spaces;

import xxl.Cell;
import xxl.Range;
import xxl.content.literals.Literal;
import xxl.content.literals.Str;
import xxl.datastructure.DataStructure;
import xxl.exceptions.InvalidGammaException;
import xxl.visits.CellVisitor;

/**
 * Class representing the coalesce function.
 */
public class Coal extends SpacesFunction {

    /**
     * Contructor.
     * 
     * @param range
     * @param ds
     */
    public Coal(Range range, DataStructure ds) throws InvalidGammaException {
        super(range, ds);
        setName("COALESCE");
    }

    /**
     * @see xxl.content.Content#value()
     */
    public Literal value() {
        Cell[] cells = getCells();
        for (Cell cell : cells) {
            try {
                return new Str(((Str)cell.value()).getValue());
            } catch (ClassCastException e) {
                // do nothing
            }
        }
        return new Str("");
    };

    public void accept(CellVisitor visitor) {
        visitor.visitCoalFunction(this);
    }

    @Override
    public String toString() {
        return "COALESCE" + super.toString();
    }
}