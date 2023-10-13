package xxl.content.literals;

/**
 * Class representing an invalid value.
 */
public class InvalidValue extends Literal {

    /**
     * @see xxl.content.Content#showValue()
     */
    @Override
    public String showValue() {
        return "#VALUE";
    }
    
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
}