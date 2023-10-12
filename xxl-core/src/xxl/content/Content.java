package xxl.content;

import java.io.Serializable;

import xxl.content.literals.Literal;

public abstract class Content implements Serializable {

    /**
     * 
     * @return the value of the content
     */
    public abstract Literal value();
}