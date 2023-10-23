package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.exceptions.InvalidGammaException;
import xxl.Spreadsheet;
// FIXME import classes

/**
 * Paste command.
 */
class DoPaste extends Command<Spreadsheet> {

    DoPaste(Spreadsheet receiver) {
        super(Label.PASTE, receiver);
        addStringField("cell", Prompt.address());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.paste(stringField("cell"));
        } catch (InvalidGammaException e) {
            throw new InvalidCellRangeException(stringField("cell"));
        }
    }

}
