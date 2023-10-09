package xxl.ds;

import java.util.ArrayList;
import java.util.List;

import xxl.Cell;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.utils.Position;
import xxl.utils.Parser;

public class DS1 extends DS {

    /** List of cells. */
    private List<Cell> cells;

    public DS1() {

    }

    public DS1(int nRows, int nColumns) {
        cells = new ArrayList<Cell>(nRows * nColumns); // TODO Iterador para meter celuals bazias

        setColumns(nColumns);
        setRows(nRows);
    }

    public Cell getCell(String coords) {
        Position pos = new Position(coords);
        return getCell(pos);
    }

    public Cell getCell(Position pos) {
        return cells.get((pos.getRow() - 1) * getNColumns() + (pos.getColumn() - 1));
    }

    public Cell[] getCells(String coords, String coords2) {
        String c = coords + ":" + coords2;
        return getCells(c);
    }

    public Cell[] getCells(String coords) throws UnrecognizedEntryException { // FIXME
        Parser p = new Parser();
        Position[] pos = p.parseRange(coords);
        Cell[] res = new Cell[pos.length];
        int i = 0;
        for (Position po : pos) {
            Cell c = getCell(po);
            res[i] = c;
        }
        return res;

    }
}