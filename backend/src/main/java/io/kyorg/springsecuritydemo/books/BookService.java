package io.kyorg.springsecuritydemo.books;

import javassist.tools.web.BadHttpRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

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
            Book old = oldBook.get();

            old.setIsbn(book.getIsbn());
            old.setDescription(book.getDescription());
            old.setTitle(book.getTitle());
            old.setLanguage(book.getLanguage());
            old.setNumPages(book.getNumPages());
            old.setPublisher(book.getPublisher());

            return bookRepository.save(old);
        } else {
            throw new BookNotFoundException();
        }
    }
}
