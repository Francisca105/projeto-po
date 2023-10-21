package xxl.content;

import xxl.Address;
import xxl.Cell;
import xxl.content.literals.Literal;
import xxl.datastructure.DataStructure;
import xxl.exceptions.InvalidGammaException;

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
     * @return the adrress of the referenced cell
     */
    public Address getAddress() {
        return _ref;
    }

    /**
     * @see xxl.content.Content#value()
     */
    @Override
    public Literal value() {
        Cell cell = _cell;
        if(cell.getContent() == null)
            return null;
        return cell.getContent().value();
    }

    /**
     * @see xxl.content.Content#showValue()
     */
    @Override
    public String showValue() {
        if(_cell.getContent() == null || value() == null)
            return "#VALUE=" + _ref.toString();

        return value() + "=" + _ref.toString();
    }

    @Override
    public String toString() {
        return "=" + _ref.toString();
    }
}
