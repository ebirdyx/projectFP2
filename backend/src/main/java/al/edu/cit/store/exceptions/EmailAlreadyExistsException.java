package al.edu.cit.store.exceptions;

public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException() {
        super("email already exists!");
    }
}
