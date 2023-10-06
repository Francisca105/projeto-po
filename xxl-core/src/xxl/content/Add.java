package xxl.content;

public class Add extends Function {
    private Content<?> _n1;
    private Content<?> _n2;

    public Add(Content<?> n1, Content<?> n2) {
        _n1 = n1;
        _n2 = n2;
    }

    public Content<?> getN1() {
        return _n1;
    }

    public Content<?> getN2() {
        return _n2;
    }

}