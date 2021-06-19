package io.kyorg.springsecuritydemo.books;

import javassist.tools.web.BadHttpRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks()
                .stream()
                .map(book -> mapper.map(book, BookResponse.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) throws BookNotFoundException {
        return ResponseEntity.ok(mapper.map(bookService.getBook(id), BookResponse.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) throws BookNotFoundException {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookRequest bookRequest) throws BadHttpRequest {
        Book newBook = bookService.createBook(mapper.map(bookRequest, Book.class));
        return ResponseEntity.ok(mapper.map(newBook, BookResponse.class));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest)
            throws BookNotFoundException {

        Book newBook = bookService.updateBook(id, mapper.map(bookRequest, Book.class));
        return ResponseEntity.ok(mapper.map(newBook, BookResponse.class));
    }
}
