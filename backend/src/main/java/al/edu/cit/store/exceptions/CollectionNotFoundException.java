package al.edu.cit.store.exceptions;

public class CollectionNotFoundException extends Exception {
    public CollectionNotFoundException() {
        super("book collection not found!");
    }
}
