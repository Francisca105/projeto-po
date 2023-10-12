package xxl.content.functions.interval.spaces;

import xxl.content.Content;
import xxl.content.functions.FunctionNames;
import xxl.content.literals.Literal;

public class Coal extends SpacesFunction {

    /**
     * Contructor.
     * 
     * @param arg1
     * @param arg2
     */
    public Coal (Content arg1, Content arg2) {
        super(arg1, arg2);
        setName(FunctionNames.COAL);
    }

    public Literal value() {
        return null; // TODO: implemet
    };
}