package xxl.content.functions.interval.nospaces;

import xxl.content.Content;
import xxl.content.literals.Literal;
import xxl.content.functions.FunctionNames;

public class Avg extends NoSpacesFunction {
    /**
     * Constructor
     * 
     * @param arg1
     * @param arg2
     */
    public Avg(Content arg1, Content arg2) {
        super(arg1, arg2);
        setName(FunctionNames.AVG);
    }

    /**
     * @see xxl.content.functions.Function#value()
     */
    public Literal value() {
        return null; // TODO: implemet
    };
}