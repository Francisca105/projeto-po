package xxl.content.literals;

public class Int extends Literal {

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
}