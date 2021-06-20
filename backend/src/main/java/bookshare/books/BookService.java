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
    private final ModelMapper mapper;
    private final StorageService storageService;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void deleteBook(Long id) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new BookNotFoundException();
        }
    }

    public Book createBook(Book book) throws BadHttpRequest {
        return bookRepository.save(book);
    }

    public Book getBook(Long id) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            return book.get();
        } else {
            throw new BookNotFoundException();
        }
    }

    public Book updateBook(Long id, Book book) throws BookNotFoundException {
        Optional<Book> oldBook = bookRepository.findById(id);
        if (oldBook.isPresent()) {
            Book updatedBook = mapper.map(book, Book.class);
            updatedBook.setId(oldBook.get().getId());
            return bookRepository.save(updatedBook);
        } else {
            throw new BookNotFoundException();
        }
    }

    public Book store(Long bookId, MultipartFile content) throws BookNotFoundException {
        Optional<Book> item = bookRepository.findById(bookId);

        if (item.isEmpty())
            throw new BookNotFoundException();

        // remove old book if exist
        Book book = item.get();
        if (book.getFileName() != null)
            storageService.deleteFile(book.getFileName());

        // save new book to disk
        storageService.save(content);

        // update book filename inside book
        book.setFileName(content.getOriginalFilename());
        return bookRepository.save(book);
    }

    public Resource load(Long bookId) throws BookNotFoundException {
        Optional<Book> item = bookRepository.findById(bookId);

        if (item.isEmpty())
            throw new BookNotFoundException();

        return storageService.load(item.get().getFileName());
    }
}
