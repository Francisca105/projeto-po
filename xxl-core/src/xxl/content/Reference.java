package xxl.content;

import xxl.Address;
import xxl.Cell;
import xxl.content.literals.Literal;
import xxl.datastructure.DataStructure;
import xxl.exceptions.InvalidGammaException;
import xxl.visits.CellVisitor;

/**
 * Class representing a reference.
 */
public class Reference extends Content {

    private Cell _cell;
    
    private Address _ref;

    /**
     * Constructor.
     * 
     * @param ref
     * @param ds
     */
    public Reference(Address ref, DataStructure ds) throws InvalidGammaException {
        _ref = ref;
        _cell = ds.getCell(ref);
    }

    /**
     * Constructor.
     * 
     * @param ref
     * @param cell
     */
    public Reference(Address ref, Cell cell) {
        _ref = ref;
        _cell = cell;
    }

    /**
     * @return the address of the referenced cell
     */
    public Address getAddress() {
        return _ref;
    }

    /**
     * 
     * @return the referenced cell
     */
    public Cell getCell() {
        return _cell;
    }

    /**
     * @see xxl.content.Content#value()
     */
    @Override
    public Literal value() {
        if(_cell.getContent() == null)
            return null;
        return _cell.getContent().value();
    }

    @Override
    public String toString() {
        return /* "=" + */_ref.toString();
    }

    public String string() {
        return "=" + toString();
    }

    public void accept(CellVisitor visitor) {
        visitor.visitReference(this);
    }
}
