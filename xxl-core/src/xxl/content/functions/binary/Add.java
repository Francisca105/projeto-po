package xxl.content.functions.binary;

import xxl.content.Content;
import xxl.content.literals.Int;
import xxl.content.literals.InvalidValue;
import xxl.content.literals.Literal;
import xxl.content.functions.FunctionNames;

public class Add extends BinaryFunction {

    /**
     * Constructor
     * 
     * @param arg1
     * @param arg2
     */
    public Add (Content arg1, Content arg2) {
        super(arg1, arg2);
        setName(FunctionNames.ADD);
    }

    /**
     * @see xxl.content.functions.Function#value()
     */
    public Literal value() {
        if (hasValidArguments())
            return new Int(parseIntValue(getFirstArg()) + parseIntValue(getSecondArg()));
        return new InvalidValue();
    }
}