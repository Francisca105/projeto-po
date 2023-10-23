package xxl.app.search;

import pt.tecnico.uilib.menus.Command;

import xxl.Spreadsheet;
import xxl.app.visitors.RenderCell;
import xxl.Cell;
import xxl.content.literals.Literal;

import java.util.function.Predicate;

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
        RenderCell renderer = new RenderCell();
        _receiver.searchV(stringField("value"), renderer);
        if (renderer.getRendering().size() != 0)
            _display.popup(renderer.getRendering());
    }
}
