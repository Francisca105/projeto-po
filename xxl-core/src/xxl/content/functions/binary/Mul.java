package xxl.content.functions.binary;

import xxl.content.literals.Literal;
import xxl.content.literals.Int;
import xxl.content.literals.InvalidValue;
import xxl.content.Content;

public class Mul extends BinaryF {
    public Mul(Content arg1, Content arg2) {
        super(arg1, arg2);
        setName("MUL");
    }

    public Literal value() {
        if (hasValidArguments())
            return new Int(((Int)_arg1).getValue() * ((Int)_arg2).getValue());
        return new InvalidValue();
    };
}