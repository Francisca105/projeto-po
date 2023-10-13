package xxl.content.literals;

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

    /**
     * @see xxl.content.Content#showValue()
     */
    @Override
    public String showValue() {
        return "'" + _value;
    }

    @Override
    public String toString() {
        return "'" + _value;
    }
}