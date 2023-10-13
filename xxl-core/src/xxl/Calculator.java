package xxl;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;

import xxl.exceptions.ImportFileException;
import xxl.exceptions.MissingFileAssociationException;
import xxl.exceptions.UnavailableFileException;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.exceptions.InvalidGamaException;

// FIXME import classes

/**
 * Class representing a spreadsheet application.
 */
public class Calculator {
    // FIXME add more fields if needed
    /** The current spreadsheet. */
    private Spreadsheet _spreadsheet = null;

    /**
     * 
     * return the spreadsheet
     */
    public Spreadsheet getSpreadsheet() {
        return _spreadsheet;
    }

    /**
     * Sets the current spreadsheet.
     * 
     * @param spreadsheet
     */
    public void setSpreadsheet(Spreadsheet spreadsheet) {
        _spreadsheet = spreadsheet;
    }

    /**
     * Saves the serialized application's state into the file associated to the current network.
     *
     * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
        String _filename = _spreadsheet.getName(); // TODO

        if (_filename == null || _filename.equals(""))
            throw new MissingFileAssociationException(); // FIXME
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)))) {
            oos.writeObject(_spreadsheet);
            //_spreadsheet.setChanged(false);
        }
    }

    /**
     * Saves the serialized application's state into the specified file. The current network is
     * associated to this file.
     *
     * @param filename the name of the file.
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
        _spreadsheet.setName(filename);
        save();
    }

    /**
     * @param filename name of the file containing the serialized application's state
     *        to load.
     * @throws UnavailableFileException if the specified file does not exist or there is
     *         an error while processing this file.
     */
    public void load(String filename) throws UnavailableFileException {
        // FIXME implement serialization method
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            if (filename == "")
                throw new UnavailableFileException(filename);
            _spreadsheet = (Spreadsheet) ois.readObject();
        } catch (UnavailableFileException e) {
            throw new UnavailableFileException(filename);
        } catch (IOException e) {
            throw new UnavailableFileException(filename);
        } catch (ClassNotFoundException e) {
            throw new UnavailableFileException(filename);
        }
        //_network.setChanged(false);
    }

    /**
     * Read text input file and create domain entities..
     *
     * @param filename name of the text input file
     * @throws ImportFileException
     */
    public void importFile(String filename) throws ImportFileException {
    
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line = reader.readLine();
                int lines, columns;
                
                // Get the spreadsheet dimensions
                if(!line.startsWith("linhas="))
                    throw new UnrecognizedEntryException(line);

                lines = Integer.parseInt(line.substring(7));

                line = reader.readLine();

                if(!line.startsWith("colunas="))
                    throw new UnrecognizedEntryException(line);

                columns = Integer.parseInt(line.substring(8));

                _spreadsheet = new Spreadsheet(lines, columns);

                line = reader.readLine();
                
                // Insert contents
                while(line != null) {
                    String[] toInsert = line.split("\\|");

                    _spreadsheet.insertContents(toInsert[0], toInsert[1]);

                    line = reader.readLine();
                }
                
            } catch (IOException e) {
                throw new IOException();
            }
        } catch (IOException | UnrecognizedEntryException /*| InvalidGamaException*/ /* FIXME maybe other exceptions */ e) {
            throw new ImportFileException(filename, e);
        }
    }

}
