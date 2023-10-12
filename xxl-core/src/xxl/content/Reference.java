package xxl.content;

import xxl.Address;
import xxl.Cell;
import xxl.content.literals.Literal;
import xxl.datastructure.DataStructure;
import xxl.exceptions.InvalidGamaException;

public class Reference extends Content {
    private Cell _cell;
    private Address _ref;

    /**
     * Constructor.
     * 
     * @param ref
     */
    public Reference(Address ref, DataStructure ds) throws InvalidGamaException {
        _ref = ref;
        _cell = ds.getCell(ref);
    }

    /**
     * @see xxl.content.Content#value()
     */
    public Literal value() {
        return _cell.getContent().value(); // TODO: implement
    }

    @Override
    public String showValue() {
        return value().showValue() + "=" + _ref.toString();
    }

    @Override
    public String toString() {
        return "=" + _ref.toString();
    }
}
