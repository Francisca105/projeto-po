package xxl.content.literals;

public class Int extends Literal {
    private int _value;

    // temporary
    public Int() {}

    public Int(int value) {
        _value = value;
    }

    public int getValue() {
        return _value;
    }

    @Override
    public String toString() {
        return Integer.toString(_value);
    }

    @Override
    public String asString() {
        return Integer.toString(_value);
    }

    @Override
    public Literal value() {
        return this;
    }
}