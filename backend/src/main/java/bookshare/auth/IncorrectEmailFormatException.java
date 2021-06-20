package bookshare.auth;

public class IncorrectEmailFormatException extends Exception {
    public IncorrectEmailFormatException() {
        super("email format incorrect");
    }
}
