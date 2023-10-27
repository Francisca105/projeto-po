package xxl.app.visitors;

import java.util.ArrayList;
import java.util.Collection;

import xxl.Cell;
import xxl.content.functions.binary.Add;
import xxl.content.functions.binary.Div;
import xxl.content.functions.binary.Mul;
import xxl.content.functions.binary.Sub;
import xxl.content.functions.interval.nospaces.Avg;
import xxl.content.functions.interval.nospaces.Prod;
import xxl.content.functions.interval.spaces.Coal;
import xxl.content.functions.interval.spaces.Conc;
import xxl.content.literals.Int;
import xxl.content.literals.Str;
import xxl.content.Reference;
import xxl.visits.CellVisitor;

/*
 * This class is used to render multiple cells to be shown to the user.
 * More specifically, it is used to render the cells that contain a specific value.
 */
public class RenderStrings extends CellVisitor {

    /** The value to search in the cells. */
    private String _value = "";

    /** The rendering of a cell. */
    private String _render = "";

    /** The rendering of cells. (can be only one) */
    private Collection<String> _rendering = new ArrayList<String>();

    /**
     * Constructor.
     * 
     * @param string
     */
    public RenderStrings(String value) {
        _value = value;
    }

    /**
     * 
     * @return the rendering of cells (can be only one)
     */
    public Collection<String> getRendering() {
        return _rendering;
    }

    /**
     * Renders a cell.
     *
     * @param cell
     * @param address
     */
    public void visitCell(Cell cell, String address) {
        if (cell.value() != null) {
            try {
                if(cell.getContent() == null)
                    return;

                Str content = (Str) cell.getContent();
                _render += address + "|";

                _render += cell.value().toString();
                content.accept(this);

                _rendering.add(_render);
                _render = "";
            } catch (ClassCastException | NullPointerException e) {
                // do nothing
            }
        }
    }

    public void visitInt(Int integer) {
        // do nothing
    }

    public void visitStr(Str string) {
        // do nothing
    }

    public void visitReference(Reference reference) {
        _render += "=" + reference.toString();
    }

    public void visitAddFunction(Add addFunction) {
        _render += "=" + addFunction.toString();
    }

    public void visitSubFunction(Sub subFunction) {
        _render += "=" + subFunction.toString();
    }

    public void visitMulFunction(Mul mulFunction) {
        _render += "=" + mulFunction.toString();
    }

    public void visitDivFunction(Div divFunction) {
        _render += "=" + divFunction.toString();
    }

    public void visitConcFunction(Conc concFunction) {
        _render += "=" + concFunction.toString();
    }

    public void visitCoalFunction(Coal coalFunction) {
        _render += "=" + coalFunction.toString();
    }

    public void visitAvgFunction(Avg avgFunction) {
        _render += "=" + avgFunction.toString();
    }

    public void visitProdFunction(Prod prodFunction) {
        _render += "=" + prodFunction.toString();
    }
}