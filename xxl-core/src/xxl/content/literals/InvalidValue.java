package xxl.content.literals;

public class InvalidValue extends Literal {

    @Override
    public String toString() {
        return "#VALUE";
    }

    public Literal value() {
        return this;
    }
}