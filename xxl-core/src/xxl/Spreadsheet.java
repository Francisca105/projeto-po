package xxl;

// FIXME import classes
import xxl.exceptions.UnrecognizedEntryException; //TODO
import java.io.Serial;
import java.io.Serializable;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;

    private int _line;

    private int _column;

    // FIXME define attributes
    // FIXME define contructor(s)
    // FIXME define methods

    /**
     * Insert specified content in specified range.
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    public void insertContents(String rangeSpecification, String contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
        //FIXME implement method
    }

}
