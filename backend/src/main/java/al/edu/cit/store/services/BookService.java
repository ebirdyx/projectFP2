package al.edu.cit.store.services;

import al.edu.cit.store.exceptions.BookNotFoundException;
import al.edu.cit.store.models.Book;
import al.edu.cit.store.repositories.BookRepository;
import javassist.tools.web.BadHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

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
        if (book.getIsbn().length() < 13)
            throw new BadHttpRequest();

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
            Book old = oldBook.get();

            old.setIsbn(book.getIsbn());
            old.setDescription(book.getDescription());
            old.setTitle(book.getTitle());
            old.setAuthor(book.getAuthor());
            old.setLanguage(book.getLanguage());
            old.setPages(book.getPages());
            old.setPublisher(book.getPublisher());

            return bookRepository.save(old);
        } else {
            throw new BookNotFoundException();
        }
    }
}
