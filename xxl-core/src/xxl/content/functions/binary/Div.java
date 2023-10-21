package xxl.content.functions.binary;

import xxl.content.Content;
import xxl.content.literals.Int;
import xxl.content.literals.InvalidValue;
import xxl.content.literals.Literal;

public class Div extends BinaryFunction {

    /**
     * Constructor
     * 
     * @param arg1
     * @param arg2
     */

    public Div(Content arg1, Content arg2) {
        super(arg1, arg2);
        setName("DIV");
    }

 /**
     * @see xxl.content.functions.Function#value()
     */
    public Literal value() {
        try {
            if (hasValidArguments())
                return new Int(parseIntValue(getFirstArg()) / parseIntValue(getSecondArg()));
        }
        catch (ClassCastException | ArithmeticException e) {
            return new InvalidValue();
        }
        return new InvalidValue();
    }
}