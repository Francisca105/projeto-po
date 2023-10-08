package xxl;

import xxl.content.Content;
import xxl.utils.Position;

public class Cell {
    private Position _position;

    private Content _content;

    private boolean _isEmpty = true;

    public Cell(int row, int column) {
        setColumn(column);
        setRow(row);
    }

    public Cell(int row, int column, Content content) {
        this(row, column);
        _content = content;
        _isEmpty = false;
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
        _content = content;
    }

    public boolean isEmpty() {
        return _isEmpty;
    }

    @Override
    public String toString() {
        return _position.toString() + "|" + _content.toString();
    }
}