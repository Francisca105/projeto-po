package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import xxl.Spreadsheet;
import xxl.exceptions.InvalidGammaException;

/**
 * Delete command.
 */
class DoDelete extends Command<Spreadsheet> {

    DoDelete(Spreadsheet receiver) {
        super(Label.DELETE, receiver);
        addStringField("range", Prompt.address());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.deleteGamma(stringField("range"));
        } catch (InvalidGammaException e) {
            throw new InvalidCellRangeException(stringField("range"));
        }
    }
}
