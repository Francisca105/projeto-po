package xxl;

import java.io.Serializable;

import xxl.content.Content;
import xxl.content.literals.InvalidValue;
import xxl.content.literals.Literal;

/**
 * Class representing a cell of a spreadsheet.
 */
public class Cell implements Serializable {

    /** Cell content. */
    private Content _content;

    /**
     * 
     * @return the cell content
     */
    public Content getContent() {
        return _content;
    }

    /**
     * Sets the cell content.
     * 
     * @param content
     */
    public void setContent(Content content) {
        _content = content;
    }

    /**
     * 
     * @return the cell value
     */
    public Literal getValue() {
        if (_content == null)
            return new InvalidValue();
        return _content.value();
    }
}