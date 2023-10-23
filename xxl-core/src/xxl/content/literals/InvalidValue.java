package xxl.content.literals;

import xxl.visits.CellVisitor;

/**
 * Class representing an invalid value.
 */
public class InvalidValue extends Literal {
    
    /**
     * @see xxl.content.Content#value()
     */
    public Literal value() {
        return this;
    }

    @Override
    public String toString() {
        return "#VALUE";
    }

    public String string() {
        return toString();
    }

    public void accept(CellVisitor visitor) {
        
    }
}