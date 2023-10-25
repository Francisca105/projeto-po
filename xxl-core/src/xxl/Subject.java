package xxl;

/** 
 * Interface representing a subject in an Observer pattern.
 */
public interface Subject {

    /**
     * Registers an observer.
     * 
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * Removes an observer.
     * 
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * Notifies all observers that their subject has changed.
     * 
     */
    void notifyObservers();
}