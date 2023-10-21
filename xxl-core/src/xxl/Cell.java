package xxl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xxl.content.Content;
import xxl.content.literals.InvalidValue;
import xxl.content.literals.Literal;

/**
 * Class representing a cell of a spreadsheet.
 */
public class Cell implements Serializable, Subject {

    /** Cell content. */
    private Content _content;

    private Literal _value;

    private List<Observer> _observers = new ArrayList<Observer>();

    /**
     * 
     * @return the cell content
     */
    public Content getContent() {
        return _content;
    }

    /**
     * Sets the cell content.
     * 
     * @param content
     */
    public void setContent(Content content) {
        _content = content;
        if (content != null)
            _value = content.value();
    }

    /**
     * 
     * @return the cell value
     */
    public Literal getValue() {
        if (_value == null)
            return new InvalidValue();
        return _value;
    }

    public void notifyObservers() {
        for (Observer o : _observers)
            o.update();
    }

    public void registerObserver(Observer o) {
        _observers.add(o);
    }

    public void removeObserver(Observer o) {
        int i = _observers.indexOf(o);
        if (i >= 0)
            _observers.remove(i);
    }
}