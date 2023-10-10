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
        setPosition(new Position(row, column));
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
     * Defines the column of the cell.
     * 
     * @param column
     */
    public void setColumn(int column) {
        _position.setColumn(column);
    }

    /**
     * @return the requested cell row.
     */
    public int getRow() {
        return _position.getRow();
    }

    /**
     * Defines the row of the cell.
     * 
     * @param row
     */
    public void setRow(int row) {
        _position.setRow(row);
    }

    /**
     * @return the requested cell position.
     */
    public Position getPosition() {
        return _position;
    }

    /**
     * Defines the position of the cell.
     * 
     * @param position
     */
    public void setPosition(Position position) {
        _position = position;
    }

    /**
     * @return the requested cell content.
     */
    public Content getContent() {
        return _content;
    }

    /**
     * Defines the content of the cell.
     * 
     * @param content
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