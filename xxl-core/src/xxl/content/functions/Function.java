package xxl.content.functions;

import xxl.content.Content;
import xxl.content.literals.Literal;

public abstract class Function extends Content {
    /** First argument */
    private Content _arg1;

    /** Secound argument */
    private Content _arg2;

    /** The name of the function */
    private String _name;

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

    /**
     * Defines the name of the function
     * 
     * @return
     */
    public void setName(String name) {
        _name = name;
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
     * @return the secound argument
     */
    public Content getSecondArg() {
        return _arg2;
    }

    /**
     * @see xxl.content.Content#value()
     */
    public abstract Literal value();


    @Override
    public String toString() {
        return "="+_name + "(" + _arg1 + ", " + _arg2 + ")";
    }

    @Override
    public String showValue() {
        return value().toString() + this.toString();
    }
}