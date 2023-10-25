package xxl.app.visitors;

import java.util.ArrayList;
import java.util.Collection;

import xxl.Cell;
import xxl.content.functions.binary.Add;
import xxl.content.functions.binary.BinaryFunction;
import xxl.content.functions.binary.Div;
import xxl.content.functions.binary.Mul;
import xxl.content.functions.binary.Sub;
import xxl.content.functions.interval.IntervalFunction;
import xxl.content.functions.interval.nospaces.Avg;
import xxl.content.functions.interval.nospaces.Prod;
import xxl.content.functions.interval.spaces.Coal;
import xxl.content.functions.interval.spaces.Conc;
import xxl.content.literals.Int;
import xxl.content.literals.Str;
import xxl.content.Reference;
import xxl.visits.CellVisitor;

public class RenderCell extends CellVisitor {

    private String _render = "";

    private Collection<String> _rendering = new ArrayList<String>();

    public Collection<String> getRendering() {
        return _rendering;
    }

    public void visitCell(Cell cell, String address) {
        _render += address + "|";
        if (cell.getContent() != null) {
            _render += cell.value().toString();
            cell.getContent().accept(this);
        }
        _rendering.add(_render);
        _render = "";
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

    public void visitBinaryFunction(BinaryFunction binaryFunction) {
        _render += "=" + binaryFunction.toString();
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

    public void visitIntervalFunction(IntervalFunction intervalFunction) {

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