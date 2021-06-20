package io.kyorg.springsecuritydemo.auth;

public class IncorrectEmailFormatException extends Exception {
    public IncorrectEmailFormatException() {
        super("email format incorrect");
    }
}
