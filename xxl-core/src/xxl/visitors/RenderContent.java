package xxl.visitors;

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
 * This class is used to render the content of a cell the same way as it's imported.
 * It is used in some features of the cutbuffer.
 */
public class RenderContent extends CellVisitor {

    /* The rendering of a cell content. */
    private String _render = "";

    /**
     * 
     * @return the rendering of the cell content
     */
    public String getRender() {
        String result = _render;
        _render = "";   // resets the rendering
        return result;
    }

    /**
     * Renders the cell content.
     * 
     */
    public void visitCell(Cell cell, String address) {
        if (cell.getContent() != null)
            cell.getContent().accept(this);
        else
            _render = "";
    }

    public void visitInt(Int integer) {
        _render += integer.toString();
    }

    public void visitStr(Str string) {
        _render += string.toString();
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