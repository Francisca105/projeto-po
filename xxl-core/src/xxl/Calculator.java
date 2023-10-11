package xxl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

import xxl.ds.DS1;
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

    public void setSpreadsheet(Spreadsheet spreadsheet) {
        _spreadsheet = spreadsheet;
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
    public void importFile(String filename) throws ImportFileException {
        // FIXME open import file and feed entries to new spreadsheet (in a cycle)
        // each entry is inserted with:
        setSpreadsheet(new Spreadsheet());

        try {//(BufferedReader reader = _parser.parseFile(filename, _spreadsheet)) {
            BufferedReader reader = _parser.parseFile(filename, _spreadsheet);
            _spreadsheet.setDS(new DS1(_spreadsheet.getNRows(), _spreadsheet.getNColumns()));
            String line= reader.readLine();
            while (line != null) {
                String pos = line.substring(0, line.indexOf("|"));
                String content = line.substring(line.indexOf("|") + 1);

                String[] posArray = pos.split(";");
                int row = Integer.parseInt(posArray[0]);
                int col = Integer.parseInt(posArray[1]);

                _spreadsheet.insertContents(String.format("%d;%d", row, col), content);
                line = reader.readLine();
            }
            /*for (int i = 1; i <= _spreadsheet.getNRows(); i++) {
                
                for (int j = 1; j <= _spreadsheet.getNColumns(); j++) {
                    String line = reader.readLine();
                    String pos = line.substring(0, line.indexOf("|"));
                    String content = line.substring(line.indexOf("|") + 1);

                    String[] posArray = pos.split(";");
                    int row = Integer.parseInt(posArray[0]);
                    int col = Integer.parseInt(posArray[1]);
                    // AHHHHH OK, 
                    try {
                        if (i == row && col == j) {
                            _spreadsheet.insertContents(String.format("%d;%d", i, j), content);
                        } else { // FIXME :( not cooking
                            int nColumns = _spreadsheet.getNColumns();
                            for (int k = 0; (nColumns - j + nColumns*(j-i-1) + i < k); k++) 
                                _spreadsheet.insertContents(String.format("%d;%d", i, j), "");

                            i = row;
                            j = col;
                        }                     } catch (Exception e) {                     catch (Exception e) {
                     /System.out.println(i+" "*+j);
                     */   //System.out.println(e);
                    
                
            
            reader.close();
            // ....
        } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
            throw new ImportFileException(filename, e);
        }
    }
}