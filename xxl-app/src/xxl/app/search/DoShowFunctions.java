package xxl.app.search;

import pt.tecnico.uilib.menus.Command;

import xxl.Spreadsheet;
import xxl.app.visitors.RenderCell;

/**
 * Command for searching function names.
 */
class DoShowFunctions extends Command<Spreadsheet> {

    DoShowFunctions(Spreadsheet receiver) {
        super(Label.SEARCH_FUNCTIONS, receiver);
        addStringField("value", Prompt.searchFunction());
    }

    @Override
    protected final void execute() {
        RenderCell renderer = new RenderCell();
        _receiver.searchF(stringField("value"), renderer);
        if (renderer.getRendering().size() != 0)
            _display.popup(renderer.getRendering());
    }
}