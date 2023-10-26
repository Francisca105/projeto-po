package xxl.app.visitors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import xxl.Cell;
import xxl.content.functions.Function;
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
import xxl.utils.FunctionComparator;
import xxl.content.Reference;
import xxl.visits.CellVisitor;

/*
 * This class is used to render multiple cells to be shown to the user.
 * More specifically, it renders the cells that contain a function with its name containing a given string.
 */
public class RenderFunction extends CellVisitor {

    /** The string to serach in the function names. */
    private String _function = "";

    /** The rendering of a cell. */
    private String _render = "";

    /** The rendering of cells. (can be only one) */
    private Collection<String> _rendering = new ArrayList<String>();

    /**
     * Constructor.
     * 
     * @param string
     */
    public RenderFunction(String function) {
        _function = function;
    }

    /**
     * 
     * @return the rendering of cells (can be only one)
     */
    public Collection<String> getRendering() {
        if(!_rendering.isEmpty()) {
            List<String> rendering = new ArrayList<String>(_rendering);
            Collections.sort(rendering, new FunctionComparator());
            return rendering;
        }   
        return _rendering;
    }

    /**
     * Renders a cell.
     *
     * @param cell
     * @param address
     */
    public void visitCell(Cell cell, String address) {
        if (cell.getContent() != null) {
            try {
                if (((Function)cell.getContent()).getName().contains(_function)) {
                    _render += address + "|";
                    if (cell.getContent() != null) {
                        _render += cell.value().toString();
                        cell.getContent().accept(this);
                    }
                    _rendering.add(_render);
                    _render = "";
                }
            } catch (ClassCastException e) {
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
        // do nothing
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