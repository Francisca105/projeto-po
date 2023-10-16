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
        int arg2 = parseIntValue(getSecondArg());
        if (hasValidArguments() && arg2 != 0) {
            return new Int(parseIntValue(getFirstArg()) / arg2);
        }
        return new InvalidValue();
    }
}