package xxl.content.functions.interval.spaces;

import xxl.content.literals.Literal;
import xxl.content.literals.Str;
import xxl.content.Content;

public class Coal extends SpacesF {
    public Coal(Content arg1, Content arg2) {
        super(arg1, arg2);
        super.setName("COALESCE");
    }
    // TODO: implement
    public Literal value() {
        return new Str();
    };
}