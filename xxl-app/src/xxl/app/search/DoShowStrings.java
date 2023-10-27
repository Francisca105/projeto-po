package xxl.app.search;

import pt.tecnico.uilib.menus.Command;
import xxl.app.visitors.RenderStrings;

import xxl.Spreadsheet;

/**
 * Command for searching content values.
 */
class DoShowStrings extends Command<Spreadsheet> {

    DoShowStrings(Spreadsheet receiver) {
        super(Label.SEARCH_STRINGS, receiver);
    }

    @Override
    protected final void execute() {
        RenderStrings renderer = new RenderStrings("strings");
        _receiver.search(renderer, "strings");
        if (renderer.getRendering().size() != 0)
            _display.popup(renderer.getRendering());
    }
}
