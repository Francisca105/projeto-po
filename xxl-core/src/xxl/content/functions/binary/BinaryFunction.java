package xxl.content.functions.binary;

import xxl.content.Content;
import xxl.content.functions.Function;
import xxl.content.literals.Int;
import xxl.content.literals.Literal;
import xxl.visits.CellVisitor;

public abstract class BinaryFunction extends Function {

    /** First argument */
    private Content _arg1;

    /** Second argument */
    private Content _arg2;

    /**
     * Constructor
     * 
     * @param arg1
     * @param arg2
     */
    public BinaryFunction(Content arg1, Content arg2) {
        _arg1 = arg1;
        _arg2 = arg2;
    }

    /**
     * 
     * @return the first argument
     */
    public Content getFirstArg() {
        return _arg1;
    }

    /**
     * 
     * @return the second argument
     */
    public Content getSecondArg() {
        return _arg2;
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

    public abstract void accept(CellVisitor visitor);

    @Override
    public String toString() {
        return "(" + getFirstArg().toString() + "," + getSecondArg().toString() + ")";
    }
}