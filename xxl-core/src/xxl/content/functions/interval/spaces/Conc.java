package xxl.content.functions.interval.spaces;

import xxl.content.literals.Literal;
import xxl.content.literals.Str;
import xxl.content.Content;

public class Conc extends SpacesF {
    public Conc(Content arg1, Content arg2) {
        super(arg1, arg2);
        super.setName("CONCAT");
    }
    // TODO: implement
    public Literal value() {
        return new Str();
    };
}