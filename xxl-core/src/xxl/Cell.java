package xxl;

import java.io.Serializable;

import xxl.content.Content;

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
}