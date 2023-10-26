package xxl.app.search;

import pt.tecnico.uilib.menus.Command;

import xxl.app.visitors.RenderValues;

import xxl.Spreadsheet;

/**
 * Command for searching content values.
 */
class DoShowValues extends Command<Spreadsheet> {

    DoShowValues(Spreadsheet receiver) {
        super(Label.SEARCH_VALUES, receiver);
        addStringField("value", Prompt.searchValue());
    }

    @Override
    protected final void execute() {
        RenderValues renderer = new RenderValues(stringField("value"));
        _receiver.search(renderer, stringField("value"));
        if (renderer.getRendering().size() != 0)
            _display.popup(renderer.getRendering());
    }
}
