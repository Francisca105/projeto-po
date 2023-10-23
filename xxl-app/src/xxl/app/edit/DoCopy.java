package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Spreadsheet;
// FIXME import classes
import xxl.exceptions.InvalidGammaException;
import xxl.exceptions.ParseFunctionException;
import xxl.exceptions.UnrecognizedEntryException;

/**
 * Copy command.
 */
class DoCopy extends Command<Spreadsheet> {

    DoCopy(Spreadsheet receiver) {
        super(Label.COPY, receiver);
        addStringField("cell", Prompt.address());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.copy(stringField("cell"));
        } catch (InvalidGammaException | UnrecognizedEntryException | ParseFunctionException e) { // TODO: exceptions
            throw new InvalidCellRangeException(stringField("cell"));
        }
    }

}
