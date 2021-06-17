package al.edu.cit.store.book;

import al.edu.cit.store.exceptions.BookNotFoundException;
import javassist.tools.web.BadHttpRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(path = "/api/v1/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("{id}")
    public Book getBooks(@PathVariable Long id) throws BookNotFoundException {
        return bookService.getBook(id);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id) throws BookNotFoundException {
        bookService.deleteBook(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) throws BadHttpRequest {
        return bookService.createBook(book);
    }

    @PatchMapping("{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) throws BookNotFoundException {
        return bookService.updateBook(id, book);
    }
}
