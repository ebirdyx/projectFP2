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

    @PostMapping("/{bookId}/upload")
    public ResponseEntity<BookStoreResponse> uploadFile(
            @PathVariable Long bookId,
            @RequestParam("file") MultipartFile file) {

        String content = "";
        try {
            bookService.store(bookId, file);

            content = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new BookStoreResponse(content));
        } catch (Exception e) {
            content = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new BookStoreResponse(content));
        }
    }

    @GetMapping("/{bookId}/download")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable Long bookId) throws BookNotFoundException {
        Resource file = bookService.load(bookId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
