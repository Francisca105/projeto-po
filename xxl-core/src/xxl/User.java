package xxl;

import java.util.Map;
import java.util.HashMap;

/**
 * Class representing a user of the application.
 */
public class User {

    /** Username of the user. */
    private String _name;

    private Map<String, Spreadsheet> _spreadsheets = new HashMap<String, Spreadsheet>();

    /**
     * Constructor.
     * 
     * @param name
     */
    public User(String name) {
        _name = name;
    }

    /**
     * 
     * @return name of the user
     */
    public String getName() {
        return _name;
    }

    /**
     * Sets the name of the user.
     * 
     * @param name
     */
    public void setName(String name) {
        _name = name;
    }

    /**
     * Adds a spreadsheet to the user collection.
     * 
     * @param spreadsheet
     */
    public void addSpreadsheet(Spreadsheet spreadsheet) {
        _spreadsheets.put(spreadsheet.getName(), spreadsheet);
    }
}