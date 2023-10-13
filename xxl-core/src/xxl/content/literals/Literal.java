package xxl.content.literals;

import xxl.content.Content;

/**
 * Abstract class representing a literal content.
 */
public abstract class Literal extends Content {

    /**
     * @see xxl.content.Content#value()
     */
    public abstract Literal value();
}