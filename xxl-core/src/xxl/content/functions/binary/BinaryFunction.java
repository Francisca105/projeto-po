package xxl.content.functions.binary;

import xxl.content.Content;
import xxl.content.functions.Function;
import xxl.content.literals.Literal;

public abstract class BinaryFunction extends Function {

    /**
     * Constructor
     * 
     * @param arg1
     * @param arg2
     */
    public BinaryFunction(Content arg1, Content arg2) {
        super(arg1, arg2);
    }

    public abstract Literal value();
}