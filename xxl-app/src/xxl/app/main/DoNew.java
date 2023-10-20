package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Calculator;
import xxl.Spreadsheet;
import xxl.exceptions.InvalidDimensionException;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

    DoNew(Calculator receiver) {
        super(Label.NEW, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        try {
        Spreadsheet spreadsheet = _receiver.getSpreadsheet();
        if (spreadsheet != null && spreadsheet.getToSave() && Form.confirm(Prompt.saveBeforeExit())) {   
            DoSave saveCommand = new DoSave(_receiver);
            saveCommand.execute();
        }
        int lines = Form.requestInteger(Prompt.lines());
        int columns = Form.requestInteger(Prompt.columns());
        _receiver.setSpreadsheet(new Spreadsheet(lines, columns));
        } 
        catch (InvalidDimensionException e) {
            // fail silently
        }
    }

}
