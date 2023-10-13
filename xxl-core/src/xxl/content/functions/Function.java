package xxl.content.functions;

import java.sql.Ref;

import xxl.content.Content;
import xxl.content.Reference;
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
        // if(_arg1.toString().contains("=")) // TODO temporary fix
        //     String _arg1.toString().replace("=", "");
        String arg1 = _arg1.toString();
        String arg2 = _arg2.toString();

        if(_arg1 instanceof Reference)
            arg1 = ((Reference) _arg1).getAddress().toString();
        if(_arg2 instanceof Reference)
            arg2 = ((Reference) _arg2).getAddress().toString();

        return "="+_name + "(" + arg1 + "," + arg2 + ")";
    }

    @Override
    public String showValue() {
        return value().toString() + this.toString();
    }
}