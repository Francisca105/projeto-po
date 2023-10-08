package xxl.content.functions.interval.nospaces;

import xxl.content.literals.Int;
import xxl.content.literals.Literal;
import xxl.content.Content;

public class Avg extends NoSpacesF {
    public Avg(Content arg1, Content arg2) {
        super(arg1, arg2);
        super.setName("AVG");
    }
    // TODO: implement
    public Literal value() {
        return new Int();
    };
}