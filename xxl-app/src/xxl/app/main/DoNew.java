package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Calculator;
import xxl.Spreadsheet;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

    DoNew(Calculator receiver) {
        super(Label.NEW, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        Spreadsheet spreadsheet = _receiver.getSpreadsheet();
        if (spreadsheet != null && spreadsheet.getToSave() && Form.confirm(Prompt.saveBeforeExit())) {   
            DoSave saveCommand = new DoSave(_receiver);
            saveCommand.execute();
        }
        int l = Form.requestInteger(Prompt.lines());
        int c = Form.requestInteger(Prompt.columns());
        _receiver.setSpreadsheet(new Spreadsheet(l,c));
    }

}
