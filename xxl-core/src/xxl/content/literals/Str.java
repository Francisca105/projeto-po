package xxl.content.literals;

import xxl.visits.CellVisitor;

/**
 * Class representing a string content.
 */
public class Str extends Literal {

    /** Value of the content. */
    private String _value = "";

    /**
     * Constructor.
     * @param value
     */
    public Str(String value) {
        _value = value;
    }

    /**
     * 
     * @return the value
     */
    public String getValue() {
        return _value;
    }

    /**
     * @see xxl.content.Content#value()
     */
    @Override
    public Literal value() {
        return this;
    }

    @Override
    public String toString() {
        return "'" + _value;
    }

    public String string() {
        return toString();
    }

    public void accept(CellVisitor visitor) {
        visitor.visitStr(this);
    }
}