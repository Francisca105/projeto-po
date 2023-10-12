package xxl.content.functions.interval.nospaces;

import xxl.content.Content;
import xxl.content.functions.FunctionNames;
import xxl.content.literals.Int;
import xxl.content.literals.InvalidValue;
import xxl.content.literals.Literal;

public class Prod extends NoSpacesFunction {
    /**
     * Constructor
     * 
     * @param arg1
     * @param arg2
     */
    public Prod(Content arg1, Content arg2) {
        super(arg1, arg2);
        setName(FunctionNames.PROD);
    }

    /**
     * @see xxl.content.functions.Function#value()
     */
    public Literal value() {
        return null; // TODO implement
    }
}