package xxl.content.literals;

import xxl.visits.CellVisitor;

/**
 * Class representing an integer content.
 */
public class Int extends Literal {

    /** Value of the content. */
    private int _value;

    /**
     * Constructor.
     * @param value
     */
    public Int(int value) {
        _value = value;
    }

    /**
     * 
     * @return the value
     */
    public int getValue() {
        return _value;
    }
    
    @Override
    public Literal value() {
        return this;
    }

    @Override
    public String toString() {
        return Integer.toString(_value);
    }

    @Override
    public void accept(CellVisitor visitor) {
        visitor.visitInt(this);
    }
}