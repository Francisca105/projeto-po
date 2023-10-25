package xxl.app.main;

import java.io.IOException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;

import xxl.Calculator;
import xxl.exceptions.*;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSave extends Command<Calculator> {

    DoSave(Calculator receiver) {
        super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
    }

    @Override
    protected final void execute() {
        try {
            try {
                _receiver.save();
            } catch (MissingFileAssociationException e) {
                saveAs();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current spreadsheet to a new file.
     * 
     * @throws IOException
     */
    private void saveAs() throws IOException {
        try {
            String filename = Form.requestString(Prompt.newSaveAs());
            while (filename.isEmpty())  // empty filename
                filename = Form.requestString(Prompt.newSaveAs());
            _receiver.saveAs(filename);
        } catch (MissingFileAssociationException e) {
            e.printStackTrace();
        }
    }

}
