package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import xxl.Spreadsheet;
// FIXME import classes
import xxl.app.visitors.RenderCell;

/**
 * Show cut buffer command.
 */
class DoShowCutBuffer extends Command<Spreadsheet> {

    DoShowCutBuffer(Spreadsheet receiver) {
        super(Label.SHOW_CUT_BUFFER, receiver);
    }

    @Override
    protected final void execute() {
        RenderCell renderer = new RenderCell();

        _receiver.acceptCellsVisitor(renderer);
        if (renderer.getRendering().size() != 0)
            _display.popup(renderer.getRendering());
    }

}
