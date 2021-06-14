package al.edu.cit.store.controllers;

import al.edu.cit.store.exceptions.BookNotFoundException;
import al.edu.cit.store.models.Book;
import al.edu.cit.store.services.BookService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBooks(@PathVariable Long id) throws BookNotFoundException {
        return bookService.getBook(id);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) throws BookNotFoundException {
        bookService.deleteBook(id);
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) throws BadHttpRequest {
        return bookService.createBook(book);
    }

    @PatchMapping("/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) throws BookNotFoundException {
        return bookService.updateBook(id, book);
    }
}
