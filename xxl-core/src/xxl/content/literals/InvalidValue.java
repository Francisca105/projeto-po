package xxl.content.literals;

import xxl.visits.CellVisitor;

/**
 * Class representing an invalid value.
 */
public class InvalidValue extends Literal {
    
    @Override
    public Literal value() {
        return this;
    }

    @Override
    public String toString() {
        return "#VALUE";
    }

    @Override
    public void accept(CellVisitor visitor) {
        // do nothing
    }
}