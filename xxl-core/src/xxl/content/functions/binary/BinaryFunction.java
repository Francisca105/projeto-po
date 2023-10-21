package xxl.content.functions.binary;

import xxl.content.Content;
import xxl.content.Reference;
import xxl.content.functions.Function;
import xxl.content.literals.Int;
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

    /**
     * Checks if the argument is valid.
     * 
     * @param arg
     * @return true if the argument is valid
     */
    public boolean checkArgument(Content arg) {
        if (arg.value() == null)
            return false;
        return true;
    }

    /**
     * Checks if the arguments are valid
     * 
     * @return true if the arguments are valid
     */
    public boolean hasValidArguments() {
        return (checkArgument(getSecondArg())) && checkArgument(getFirstArg());
    }

    /**
     * Converts the value of the argument to an integer
     * This is only going to be used for the arguments of the function with Integer values
     * 
     * @param arg
     * @return the value of the argument as an integer
     */
    public int parseIntValue(Content arg) throws ClassCastException {
        return ((Int)arg.value()).getValue();    
    }

    public abstract Literal value();
}