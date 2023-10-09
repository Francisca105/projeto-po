package xxl;

import xxl.content.Content;
// FIXME import classes
import xxl.ds.DS;
import xxl.ds.DS1;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.utils.Parser;
import xxl.utils.Position;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;

    /** Data structure for the spreadsheet. */
    private DS _ds = new DS1();

    /** Data structure for the cut buffer. */
    private DS _cutBuffer;

    private Parser _parser = new Parser();

    public Spreadsheet() {
    }

    public Spreadsheet(int nRows, int nColumns) {
        _ds = new DS1(nRows, nColumns);

    }

    public void setRows(int nRows) {
        _ds.setRows(nRows);
    }

    public void setColumns(int nColumns) {
        _ds.setColumns(nColumns);
    }

    /**
     * 
     * @return the number of rows of the spreadsheet.
     */
    public int getNRows() {
        return _nRows;
    }

    /**
     * 
     * @return the number of columns of the spreadsheet.
     */
    public int getNColumns() {
        return _nColumns;
    }

    /**
     * Insert specified content in specified range.
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    public void insertContents(String rangeSpecification, String contentSpecification)
            throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
        Position[] pos = _parser.parseRange(rangeSpecification);
        for (Position p : pos) {
            Content content = _parser.parseContent(contentSpecification);
            Cell cell = new Cell(p);
            if (content != null) {
                cell.setContent(content);
            } // FIXME buscar celula e substituir valores
            /*
             * TODO
             * Inserir na spreadsheet
             */
        }
    }

}
