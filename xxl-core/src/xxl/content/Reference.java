package xxl.content;

import xxl.Cell;
import xxl.utils.Position;
import xxl.content.literals.Literal;

public class Reference extends Content {
    private Position _position;

    public Reference(Cell cell) {
        _position = cell.getPosition();
    }

    public Reference(Position position) {
        _position = position;
    }

    public Reference(int row, int column) {
        _position = new Position(row, column);
    }

    public Cell getCell() {
        return new Cell(0,0); // TODO
    }

    public Literal value() {
        return getCell().getContent().value();
    }

    /**
     * @return the value of the referenced cell as a string
     */

    public String asString() {
        return getCell().getContent().toString();
    }

    @Override
    public String toString() {
        return "=" + asString();
    }
}