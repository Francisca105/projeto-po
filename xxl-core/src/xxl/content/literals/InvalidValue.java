package xxl.content.literals;

public class InvalidValue extends Literal {

    @Override
    public String toString() {
        return "#VALUE";
    }

    @Override
    public String asString() {
        return "#VALUE";
    }

    @Override
    public Literal value() {
        return this;
    }
}