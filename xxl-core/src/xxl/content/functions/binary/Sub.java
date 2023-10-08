package xxl.content.functions.binary;

import xxl.content.literals.Literal;
import xxl.content.literals.Int;
import xxl.content.literals.InvalidValue;
import xxl.content.Content;

public class Sub extends BinaryF {
    public Sub(Content arg1, Content arg2) {
        super(arg1, arg2);
        super.setName("SUB");
    }

    public Literal value() {
        if (hasValidArguments())
            return new Int(((Int)_arg1).getValue() - ((Int)_arg2).getValue());
        return new InvalidValue();
    };
}