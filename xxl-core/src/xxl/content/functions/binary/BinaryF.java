package xxl.content.functions.binary;

import xxl.content.Content;
import xxl.content.Reference;
import xxl.content.functions.Function;
import xxl.content.literals.Int;

public abstract class BinaryF extends Function {
    BinaryF(Content arg1, Content arg2) {
        super(arg1, arg2);
    }

    public boolean checkArgument(Content arg) {
        if ((arg instanceof Reference && ((Reference)arg).getCell().isEmpty()))
            return false;
        if (arg.value() instanceof Int)
            return true;
        return false;
    }

    public boolean hasValidArguments() {
        return checkArgument(_arg1) && checkArgument(_arg2);
    }
}