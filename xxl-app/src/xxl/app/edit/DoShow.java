package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import xxl.app.visitors.RenderCell;

import xxl.Spreadsheet;
import xxl.exceptions.InvalidGammaException;

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

    DoShow(Spreadsheet receiver) {
        super(Label.SHOW, receiver);
        addStringField("range", Prompt.address());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            RenderCell renderer = new RenderCell();
            _receiver.acceptCellsRangeVisitor(renderer, stringField("range"));
            if (renderer.getRendering().size() != 0)
                _display.popup(renderer.getRendering());
        } catch (InvalidGammaException e) {
            throw new InvalidCellRangeException(stringField("range"));
        }
    }
}
