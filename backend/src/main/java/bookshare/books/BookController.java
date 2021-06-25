package bookshare.books;

import javassist.tools.web.BadHttpRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        // Get all the books from bookService
        List<Book> books = bookService.getBooks();

        // map the book list to a BookResponse list
        List<BookResponse> response = books
                .stream()
                .map(book -> mapper.map(book, BookResponse.class))
                .collect(Collectors.toList());

        // return the list of BookResponse
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long id)
            throws BookNotFoundException {

        // search for the book with id => id
        Book book = bookService.getBook(id);

        // map the returned book to a BookResponse
        BookResponse response = mapper.map(book, BookResponse.class);

        // send the response
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable Long id)
            throws BookNotFoundException {

        // Call bookService to delete the book
        bookService.deleteBook(id);

        // Return empty OK response
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest request)
            throws BadHttpRequest {

        // map a book from the request
        Book bookRequest = mapper.map(request, Book.class);

        // call bookService to create the book
        Book createdBook = bookService.createBook(bookRequest);

        // map a BookResponse from the created book
        BookResponse response = mapper.map(createdBook, BookResponse.class);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody BookRequest request)
            throws BookNotFoundException {

        // map a book from the request
        Book bookRequest = mapper.map(request, Book.class);

        // call bookService to update the book with id
        Book updatedBook = bookService.updateBook(id, bookRequest);

        // map the updated book to an BookResponse
        BookResponse response = mapper.map(updatedBook, BookResponse.class);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{bookId}/upload")
    public ResponseEntity<?> uploadFile(
            @PathVariable Long bookId,
            @RequestParam("file") MultipartFile file) {

        try {
            bookService.store(bookId, file);
            BookStoreResponse response = new BookStoreResponse(file.getOriginalFilename());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.internalServerError();
        }
    }

    @GetMapping("/{bookId}/download")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable Long bookId) throws BookNotFoundException {
        Resource file = bookService.load(bookId);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
