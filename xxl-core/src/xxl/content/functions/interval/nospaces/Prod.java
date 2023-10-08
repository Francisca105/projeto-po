package xxl.content.functions.interval.nospaces;

import xxl.content.literals.Int;
import xxl.content.literals.Literal;
import xxl.content.Content;

public class Prod extends NoSpacesF {
    public Prod(Content arg1, Content arg2) {
        super(arg1, arg2);
        super.setName("PRODUCT");
    }
    // TODO: implement
    public Literal value() {
        return new Int();
    };
}