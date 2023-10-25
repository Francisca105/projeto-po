package xxl.visits;

import xxl.Cell;
import xxl.content.Reference;
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

/**
 * This class is used to visit the content of a cell.
 */
public abstract class ContentVisitor {

    public abstract void visitCell(Cell cell, String address);

    public abstract void visitInt(Int integer);

    public abstract void visitStr(Str string);

    public abstract void visitReference(Reference reference);

    public abstract void visitAddFunction(Add addFunction);

    public abstract void visitSubFunction(Sub subFunction);

    public abstract void visitMulFunction(Mul mulFunction);

    public abstract void visitDivFunction(Div divFunction);

    public abstract void visitConcFunction(Conc concFunction);

    public abstract void visitCoalFunction(Coal coalFunction);

    public abstract void visitAvgFunction(Avg avgFunction);

    public abstract void visitProdFunction(Prod prodFunction);
}
