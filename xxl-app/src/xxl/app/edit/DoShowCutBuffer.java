package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;

import xxl.app.visitors.RenderCell;

import xxl.Spreadsheet;

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
        _receiver.showCutBuffer(renderer);
        if (renderer.getRendering().size() != 0)
            _display.popup(renderer.getRendering());
    }
}
