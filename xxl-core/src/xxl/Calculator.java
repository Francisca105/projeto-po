package xxl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

import xxl.exceptions.ImportFileException;
import xxl.exceptions.MissingFileAssociationException;
import xxl.exceptions.UnavailableFileException;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.utils.Parser;

// FIXME import classes

/**
 * Class representing a spreadsheet application.
 */
public class Calculator {
    // FIXME add more fields if needed

    /** The current spreadsheet. */
    private Spreadsheet _spreadsheet = null;

    private Parser _parser = new Parser();

    private Map<String, User> _users = new HashMap<String, User>(); // TODO: adicionar mais coisas. Podemos adicionar métodos para obter users???

    /**
     *
     * @return the spreadsheet.
     */
    public Spreadsheet getSpreadsheet() {
        return _spreadsheet;
    }

    /**
     * Saves the serialized application's state into the file associated to the
     * current network.
     *
     * @throws FileNotFoundException           if for some reason the file cannot be
     *                                         created or opened.
     * @throws MissingFileAssociationException if the current network does not have
     *                                         a file.
     * @throws IOException                     if there is some error while
     *                                         serializing the state of the network
     *                                         to disk.
     */
    public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
        // FIXME implement serialization method
    }

    /**
     * Saves the serialized application's state into the specified file. The current
     * network is
     * associated to this file.
     *
     * @param filename the name of the file.
     * @throws FileNotFoundException           if for some reason the file cannot be
     *                                         created or opened.
     * @throws MissingFileAssociationException if the current network does not have
     *                                         a file.
     * @throws IOException                     if there is some error while
     *                                         serializing the state of the network
     *                                         to disk.
     */
    public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
        // FIXME implement serialization method
    }

    /**
     * @param filename name of the file containing the serialized application's
     *                 state
     *                 to load.
     * @throws UnavailableFileException if the specified file does not exist or
     *                                  there is
     *                                  an error while processing this file.
     */
    public void load(String filename) throws UnavailableFileException {
        // FIXME implement serialization method
    }

    /**
     * Read text input file and create domain entities..
     *
     * @param filename name of the text input file
     * @throws ImportFileException
     */
    public void importFile(String filename, Spreadsheet _spreadsheet) throws ImportFileException {
        // FIXME open import file and feed entries to new spreadsheet (in a cycle)
        // each entry is inserted with:
        _spreadsheet = new Spreadsheet();

        try (BufferedReader reader = _parser.parseFile(filename, _spreadsheet)) {
            for (int i = 1; i <= _spreadsheet.getNRows(); i++) {
                for (int j = 1; j <= _spreadsheet.getNColumns(); j++) {
                    String line = reader.readLine();
                    String pos = line.substring(0, line.indexOf("|"));
                    String content = line.substring(line.indexOf("|") + 1);

                    String[] posArray = pos.split(";");
                    int row = Integer.parseInt(posArray[0]);
                    int col = Integer.parseInt(posArray[1]);

                    if (i == row && col == j) {
                        _spreadsheet.insertContents(String.format("%d;%d", i, j), content);
                    } else
                        _spreadsheet.insertContents(String.format("%d;%d", i, j), "");
                }
            }
            // ....
        } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
            throw new ImportFileException(filename, e);
        }
    }
}
