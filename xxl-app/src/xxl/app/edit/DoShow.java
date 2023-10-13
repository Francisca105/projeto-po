package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Spreadsheet;
import xxl.exceptions.InvalidGamaException;

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

    DoShow(Spreadsheet receiver) {
        super(Label.SHOW, receiver);
        addStringField("cell", Prompt.address());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _display.popup(_receiver.getCells().showRange(stringField("cell")));
         } catch (InvalidGamaException e) {
            throw new InvalidCellRangeException(stringField("cell"));
        }
    }

}
