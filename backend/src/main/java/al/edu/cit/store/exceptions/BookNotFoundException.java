package al.edu.cit.store.exceptions;

public class BookNotFoundException extends Exception {
    public BookNotFoundException() {
        super("book not found!");
    }
}
