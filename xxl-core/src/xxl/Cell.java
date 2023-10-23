package xxl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xxl.content.Content;
import xxl.content.Reference;
import xxl.content.functions.Function;
import xxl.content.literals.Int;
import xxl.content.literals.Str;
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
     * Constructor.
     */
    public Cell() {
        _content = null;
        _value = null;
    }

    /**
     * Constructor of an copy of the given cell.
     * 
     * @param cell
     */
    public Cell(Cell cell) {
        /*// Copy the value
        _value = copyLiteral(cell._value);

        // Copy the observers
        _observers = new ArrayList<Observer>(cell.getObservers()); // FIXME: é assim?
        
        // Copy the subject
        _subject = cell._subject; // FIXME : é assim?

        // Copy the content
        if(cell.getContent() != null) {
            try {
                Function content = (Function) cell.getContent();
                
            } catch (Exception e) {
                // TODO: handle exception
            }
            try {
                Literal content = (Literal) cell.getContent();
                _content = copyLiteral(content);
            } catch (ClassCastException e) {
                // TODO: handle exception
            }
        }*/

        // TODO : temporario
        _content = cell.getContent();
        _value = cell.value();
        _observers = cell.getObservers();
        _subject = cell._subject;

    }

    public Reference copyReference(Reference r) {
        return new Reference(r.getAddress(), new Cell(r.getCell()));
    }

    public Literal copyLiteral(Literal l) {
        boolean is_int = false;
        Literal value;
        try {
            Int intValue = (Int) l; // FIXME: this is a hack :O
            is_int = true;
        } catch(ClassCastException e) { /* */ }
        if(is_int)
            value = new Int(((Int) l).getValue());
        else
            value = new Str(new String(((Str) l).getValue()));

        return value;
    }

    /**
     * 
     * @return the cell content
     */
    public Content getContent() {
        return _content;
    }

    /**
     * 
     * @return the observers of the cell
     */
    public List<Observer> getObservers() {
        return _observers;
    }

    /**
     * Sets the cell content.
     * 
     * @param content
     */
    public void setContent(Content content) {
        System.out.println("Cell.setContent()");
        _content = content;
        _value = (content == null) ? null : content.value();
        System.out.println("Cell.setContent(): value");
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