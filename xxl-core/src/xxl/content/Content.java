package xxl.content;

import java.io.Serializable;

import xxl.content.literals.Literal;
import xxl.visits.CellVisitor;

/**
 * Abstract class representing a cell content.
 */
public abstract class Content implements Serializable {

    /**
     * 
     * @return the value of the content
     */
    public abstract Literal value();

    /**
     * 
     * @return the String representation of the content
     */

    public abstract void accept(CellVisitor visitor);

    public abstract String string();
}