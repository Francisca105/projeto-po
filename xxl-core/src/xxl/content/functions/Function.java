package xxl.content.functions;

import xxl.content.Content;
import xxl.content.literals.Literal;

public abstract class Function extends Content {
    /** First argument */
    private Content _arg1;

    /** Secound argument */
    private Content _arg2;

    /**
     * Constructor
     * 
     * @param arg1
     * @param arg2
     */
    public Function(Content arg1, Content arg2) {
        _arg1 = arg1;
        _arg2 = arg2;
    }

    public abstract Literal value();
}