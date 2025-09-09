// DocumentSubject.java
public interface DocumentSubject {
    void addObserver(DocumentObserver observer);
    void removeObserver(DocumentObserver observer);
    void notifyObservers(DocumentJob job, String message);
}
