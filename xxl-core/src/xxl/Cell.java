package xxl;

import xxl.content.Content;

public class Cell {

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
     * Defines the cell content
     * @param content
     */
    public void setContent(Content content) {
        _content = content;
    }
}