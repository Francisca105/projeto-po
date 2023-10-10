package xxl.content.literals;

public class Str extends Literal {
    private String _value;

    // temporary
    public Str() {
    }

    public Str(String value) {
        _value = value;
    }

    public String getValue() {
        return _value;
    }

    @Override
    public String asString() {
        return "'" + _value;
    }

    @Override
    public String toString() {
        return _value;
    }

    @Override
    public Literal value() {
        return this;
    }
}