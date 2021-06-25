package bookshare.books;

import bookshare.storage.StorageService;
import javassist.tools.web.BadHttpRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final StorageService storageService;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void deleteBook(Long id) throws BookNotFoundException {
        // Search for the book
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            // Delete the book from the repository
            bookRepository.deleteById(id);
        } else {
            throw new BookNotFoundException();
        }
    }

    public Book createBook(Book book) throws BadHttpRequest {
        return bookRepository.save(book);
    }

    public Book getBook(Long id) throws BookNotFoundException {
        // Search for the book
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            // return the book if present
            return book.get();
        } else {
            throw new BookNotFoundException();
        }
    }

    public Book updateBook(Long id, Book book) throws BookNotFoundException {
        // Search for the book
        Optional<Book> oldBook = bookRepository.findById(id);

        if (oldBook.isPresent()) {
            // set the id of the book to the present book id
            book.setId(oldBook.get().getId());

            // save the book in the database
            return bookRepository.save(book);
        } else {
            throw new BookNotFoundException();
        }
    }

    public Book store(Long bookId, MultipartFile file) throws BookNotFoundException {
        // Search for the book with id == bookId
        Optional<Book> item = bookRepository.findById(bookId);

        // Check if the book exist
        if (item.isEmpty())
            throw new BookNotFoundException();

        // remove old book if exist
        Book book = item.get();
        if (book.getFileName() != null)
            storageService.deleteFile(book.getFileName());

        // save new book to disk
        storageService.save(file);

        // update book filename inside book
        book.setFileName(file.getOriginalFilename());
        return bookRepository.save(book);
    }

    public Resource load(Long bookId) throws BookNotFoundException {
        // Search for the book with id == bookId
        Optional<Book> item = bookRepository.findById(bookId);

        // Check if the book exist
        if (item.isEmpty())
            throw new BookNotFoundException();

        Book book = item.get();

        // call storageService to download the book with filename == book.getFileName()
        return storageService.load(book.getFileName());
    }
}
