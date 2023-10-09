package xxl;

import xxl.content.Content;
import xxl.utils.Position;

public class Cell {

    /** Coordinates of the cell. */
    private Position _position;

    /** Content of the cell. */
    private Content _content;

    /** Is the cell empty of content? */
    private boolean _hasContent;

    public Cell(int row, int column) {
        setColumn(column);
        setRow(row);
    }

    public Cell(int row, int column, Content content) {
        this(row, column);
        _content = content;
        _hasContent = true;
    }

    public Cell (Position p) {
        this(p.getRow(), p.getColumn());
    }

    public Cell () {
    }

    /**
     * @return the requested position.
     */
    public int getColumn() {
        return _position.getColumn();
    }

    /**
     * Define the column.
     * 
     * @param column
     * @return void
     */
    public void setColumn(int column) {
        _position.setColumn(column);
    }

    /**
     * @return the requested row.
     */
    public int getRow() {
        return _position.getRow();
    }

    /**
     * Define the row.
     * 
     * @param row
     * @return void
     */
    public void setRow(int row) {
        _position.setRow(row);
    }

    /**
     * @return the requested position.
     */
    public Position getPosition() {
        return _position;
    }

    /**
     * @return the requested cell content.
     */
    public Content getContent() {
        return _content;
    }

    /**
     * Define the content.
     * 
     * @param content
     * @return void
     */
    public void setContent(Content content) {
        if (content != null)
            _hasContent = true;
        else 
            _hasContent = false;

        _content = content;
    }

    /**
     * @return true if the cell has content.
     */
    public boolean hasContent() {
        return _hasContent;
    }

    @Override
    public String toString() {
        return _position.toString() + "|" + _content.toString();
    }
}