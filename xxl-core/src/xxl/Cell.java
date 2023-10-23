package xxl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xxl.content.Content;
import xxl.content.literals.InvalidValue;
import xxl.content.literals.Literal;
import xxl.visits.CellVisitor;

/**
 * Class representing a cell of a spreadsheet.
 */
public class Cell implements Serializable, Subject, Observer {

    /** Cell content. */
    private Content _content;

    /** Cell value. */
    private Literal _value;

    /** List of observers of the cell. */
    private List<Observer> _observers = new ArrayList<Observer>();

    private Subject _subject;

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
        _value = (content == null) ? null : content.value();
        notifyObservers();
    }

    /**
     * 
     * @return the cell value
     */
    public Literal value() {
        if (_value == null)
            return new InvalidValue();
        return _value;
    }

    /**
     * Notifies all observers of the cell.
     * 
     */
    public void notifyObservers() {
        for (Observer o : _observers)
            o.update();
    }

    /**
     * Registers an observer to the cell.
     * 
     * @param o
     */
    public void registerObserver(Observer observer) {
        _observers.add(observer);
    }

    /**
     * Removes an observer from the cell.
     * 
     * @param o
     */
    public void removeObserver(Observer observer) {
        int i = _observers.indexOf(observer);
        if (i >= 0)
            _observers.remove(i);
    }

    /**
     * Updates the cell value if the content of a cell it depends on has changed.
     * 
     */
    public void update() {
        _value = _content.value();
        notifyObservers();
    }

    public void unsubscribe() {
        if (_subject != null)
            _subject.removeObserver(this);
    }

    /**
     * @param visitor
     * @param address
     */
    public void accept(CellVisitor visitor, String address) {
        visitor.visitCell(this, address);
    }
}