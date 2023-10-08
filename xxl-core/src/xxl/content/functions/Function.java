package xxl.content.functions;

import xxl.content.Content;

public abstract class Function extends Content {
    private String _name;

    protected Content _arg1;

    protected Content _arg2;

    public Function(Content arg1, Content arg2) {
        _arg1 = arg1;
        _arg2 = arg2;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public abstract boolean hasValidArguments();

    @Override
    public String toString() {
        return "=" + getName() + "(" + _arg1.toString() + "," + _arg2.toString() + ")";
    }
    // TODO: fazer diferente
    @Override
    public String asString() {
        return "=" + getName() + "(" + _arg1.toString() + "," + _arg2.toString() + ")";
    }
}