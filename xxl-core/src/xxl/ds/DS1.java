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

    /*
     * Creates a new data structure.
     */
    public DS1() {}

    /**
     * Creates a new data structure with the specified number of rows and columns.
     * 
     * @param nRows
     * @param nColumns
     */
    public DS1(int nRows, int nColumns) {
        cells = new ArrayList<Cell>();

        setRows(nRows);
        setColumns(nColumns);

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++)
                cells.add(i * nColumns + j, new Cell(i + 1, j + 1));
        }
    }

    /**
     * 
     * @param coords
     * @return the requested cell of the DS.
     */
    public Cell getCell(String coords) {
        Position pos = new Position(coords);
        return getCell(pos);
    }

    /**
     * 
     * @param pos
     * @return the requested cell of the DS.
     */
    public Cell getCell(Position pos) {
        return cells.get((pos.getRow() - 1) * getNColumns() + (pos.getColumn() - 1));
    }
    // TODO
    /*public Cell[] getCells(String coords, String coords2) {
        String c = coords + ":" + coords2;
        return new Cell[1];
    }*/
    // TODO
    /*public Cell[] getCells(String coords) throws UnrecognizedEntryException { // TODO
        Parser p = new Parser();
        Position[] pos = p.parseRange(coords);
        Cell[] res = new Cell[pos.length];
        int i = 0;
        for (Position po : pos) {
            Cell c = getCell(po);
            res[i] = c;
        }
        return res;
    }*/
}