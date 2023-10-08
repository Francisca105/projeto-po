package xxl.content;

import java.io.Serializable;

import xxl.content.literals.Literal;

public abstract class Content implements Serializable {

    public abstract Literal value();

    public abstract String asString();
}