package xxl.content.literals;

public class Str extends Literal {

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

    @Override
    public Literal value() {
        return this;
    }

    @Override
    public String showValue() {
        return "'" + _value;
    }

    @Override
    public String toString() {
        return "'" + _value;
    }
}