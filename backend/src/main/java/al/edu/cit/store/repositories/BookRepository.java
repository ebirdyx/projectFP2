package al.edu.cit.store.repositories;

import al.edu.cit.store.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    List<Book> findAll();

    @Override
    Optional<Book> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    Book save(Book book);
}
