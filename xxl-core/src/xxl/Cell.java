package xxl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xxl.content.Content;
import xxl.content.literals.Literal;
import xxl.content.literals.InvalidValue;
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

    /** Subject of the cell. */
    private Subject _subject;

    /**
     * Constructor.
     * 
     */
    public Cell() {
        _content = null;
        _value = null;
    }

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
     * 
     * @return a list containing the cell observers
     */
    public List<Observer> getObservers() {
        return _observers;
    }
    
    /**
     * Notifies all observers of the cell that the cell has changed.
     * 
     */
    public void notifyObservers() {
        for (Observer o : _observers)
            o.update();
    }

    /**
     * Registers an observer to the cell.
     * 
     * @param observer
     */
    public void registerObserver(Observer observer) {
        _observers.add(observer);
    }

    /**
     * Removes an observer from the cell.
     * 
     * @param observer
     */
    public void removeObserver(Observer observer) {
        int i = _observers.indexOf(observer);
        if (i >= 0)
            _observers.remove(i);
    }
    
    /**
     * Updates the cell value if the content of the cell it depends on has changed.
     * 
     */
    public void update() {
        _value = _content.value();
        notifyObservers();
    }

    /**
     * If this cell depends on another cell, it stops that dependecy.
     * 
     */
    public void unsubscribe() {
        if (_subject != null)
            _subject.removeObserver(this);
    }

    /**
     * Accepts a visitor.
     * 
     * @param visitor
     * @param address
     */
    public void accept(CellVisitor visitor, String address) {
        visitor.visitCell(this, address);
    }

    public void accept(CellVisitor visitor) {
        visitor.visitCell(this);
    }

    /*public void accept(ContentVisitor visitor) { // TODO : contentvisitor
        visitor.visitCell(this);
    }*/
}