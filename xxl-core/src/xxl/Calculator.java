package xxl;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;

import xxl.exceptions.ImportFileException;
import xxl.exceptions.InvalidDimensionException;
import xxl.exceptions.MissingFileAssociationException;
import xxl.exceptions.ParseFunctionException;
import xxl.exceptions.UnavailableFileException;
import xxl.exceptions.UnrecognizedEntryException;

/**
 * Class representing a spreadsheet application.
 */
public class Calculator {

    /** The current spreadsheet. */
    private Spreadsheet _spreadsheet = null;

    /** Collection of all users of the calculator. */
    private Map<String, User> _users = new HashMap<String, User>();

    /** Current user of the calculator. */
    private User _user = new User("root");

    /**
     * Adds a user to the collection of users of the calculator.
     * 
     * @param user
     */
    public void addUser(User user) {
        _users.put(user.getName(), user);
    }

    /**
     * Sets the current user of the calculator.
     * 
     * @param user
     */
    public void setUser(User user) {
        _user = user;
    }

    /**
     * 
     * @return the current user of the calculator
     */
    public User getUser() {
        return _user;
    }

    /**
     * 
     * @return the spreadsheet
     */
    public Spreadsheet getSpreadsheet() {
        return _spreadsheet;
    }

    /**
     * 
     * @param spreadsheet
     * @return true if the spreadsheet was updated or not
     */
    public boolean getToSave() {
        Spreadsheet s = getSpreadsheet();
        if(s == null)
            return false;
            
        return s.getToSave();
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
        String _filename = _spreadsheet.getName();

        if (_filename == null || _filename.equals(""))
            throw new MissingFileAssociationException();
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)))) {
            oos.writeObject(_spreadsheet);
            _spreadsheet.setToSave(false);
            _user.addSpreadsheet(_spreadsheet);
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
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            if (filename == "")
                throw new UnavailableFileException(filename);

            _spreadsheet = (Spreadsheet) ois.readObject();
            _spreadsheet.setToSave(false);

        } catch (UnavailableFileException | IOException | ClassNotFoundException e) {
            throw new UnavailableFileException(filename);
        }
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
                _spreadsheet.setToSave(true);

                line = reader.readLine();

                // Insert contents
                while(line != null) {
                    String[] toInsert = line.split("\\|");
                    _spreadsheet.insertContents(toInsert[0], toInsert.length > 1 ? toInsert[1] : null);
                    line = reader.readLine();
                }                
            } catch (IOException e) {
                throw new IOException();
            }
        } catch (IOException | UnrecognizedEntryException | InvalidDimensionException | ParseFunctionException e) {
            throw new ImportFileException(filename, e);
        }
    }
}
