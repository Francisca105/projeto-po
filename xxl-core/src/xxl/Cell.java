package xxl;

import xxl.content.Content;

public class Cell {
    private Content<?> _content;

    private int _column;

    private int _row;

    /**
     * @return the requested cell content.
     */
    public Content<?> getContent() {
            return _content;
    }

    public void setContent(Content<?> content) {
            _content = content;
    }

    public int getColumn() {
            return _column;
    }

    public void setColumn(int column) {
            _column = column;
    }

    public int getRow() {
            return _row;
    }

    public void setRow(int row) {
            _row = row;
    }

    public Cell(int row, int column, Content<?> content) {
            _row = row;
            _column = column;
            _content = content;
    }

    public Cell(int row, int column) {
            _row = row;
            _column = column;
    }

    

    @Override
    public String toString() {
            return _content.toString();
    }


}
