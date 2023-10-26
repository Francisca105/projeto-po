package xxl.app.search;

import pt.tecnico.uilib.forms.Form;

import pt.tecnico.uilib.menus.Command;

import xxl.app.visitors.RenderCell;
import xxl.app.visitors.RenderFunction;
import xxl.utils.FunctionComparator;
import xxl.Spreadsheet;

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
        RenderFunction renderer = new RenderFunction(stringField("value"));
        _receiver.search(renderer, stringField("value"));
        if (renderer.getRendering().size() != 0)
            _display.popup(renderer.getRendering());
    }
}