package xxl.content;

public class Content<T> {
    private T _content;
    

    public Content(T content) {
        setContent(content);
    }

    public Content() { }

    /**
     * @param
     * @return void
    */
    protected void setContent(T content) {
        _content = content;
    }

    /**
     * @return requested content
     */
    public T getContent() {
        return _content;
    }
}