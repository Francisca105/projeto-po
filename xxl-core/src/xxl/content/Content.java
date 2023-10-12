package xxl.content;

import java.io.Serializable;

import xxl.content.functions.binary.Add;
import xxl.content.functions.binary.Div;
import xxl.content.functions.binary.Mul;
import xxl.content.functions.binary.Sub;
import xxl.content.functions.interval.nospaces.Avg;
import xxl.content.functions.interval.nospaces.Prod;
import xxl.content.functions.interval.spaces.Coal;
import xxl.content.functions.interval.spaces.Conc;
import xxl.content.literals.Int;
import xxl.content.literals.Literal;
import xxl.content.literals.Str;
import xxl.exceptions.UnrecognizedEntryException;

public abstract class Content implements Serializable {

    /**
     * 
     * @return the value of the content
     */
    public abstract Literal value();

    /**
     * @return the String representation of the content
     */
    public abstract String showValue();
}