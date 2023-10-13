package xxl.app.main;

import java.io.IOException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Calculator;
// FIXME import classes
import xxl.exceptions.UnavailableFileException;

/**
 * Open existing file.
 */
class DoOpen extends Command<Calculator> {

    DoOpen(Calculator receiver) {
        super(Label.OPEN, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        // try {
            //FIXME implement command
            if (Form.confirm(Prompt.saveBeforeExit())) {
                DoSave saveCommand = new DoSave(_receiver);
                saveCommand.execute();
            }
        // } catch (UnavailableFileException e) {
        //     throw new FileOpenFailedException(e);
        // }
    }
}
