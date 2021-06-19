package io.kyorg.springsecuritydemo.books;

public class BookNotFoundException extends Exception {
    public BookNotFoundException() {
        super("book not found!");
    }
}
