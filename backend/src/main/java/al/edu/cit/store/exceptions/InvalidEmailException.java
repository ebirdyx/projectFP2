package al.edu.cit.store.exceptions;

public class InvalidEmailException extends Exception {
    public InvalidEmailException() {
        super("invalid email address!");
    }
}
