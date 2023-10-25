package xxl.content.functions.interval.spaces;

import xxl.Cell;
import xxl.Range;
import xxl.content.literals.Literal;
import xxl.content.literals.Str;
import xxl.datastructure.DataStructure;
import xxl.exceptions.InvalidGammaException;
import xxl.visits.CellVisitor;

/**
 * Class representing the concatenate function.
 */
public class Conc extends SpacesFunction {

    /**
     * Constructor
     * 
     * @param arg1
     * @param arg2
     */
    public Conc(Range range, DataStructure ds) throws InvalidGammaException {
        super(range, ds);
        setName("CONCAT");
    }

    @Override
    public Literal value() {
        String result = "";
        Cell[] cells = getCells();
        for (Cell cell : cells) {
            try {
                result += ((Str)cell.value()).getValue();
            } catch (ClassCastException e) {
                // do nothing
            }
        }
        return new Str(result);
    }

    @Override
    public String toString() {
        return "CONCAT" + super.toString();
    }

    @Override
    public void accept(CellVisitor visitor) {
        visitor.visitConcFunction(this);
    }
}