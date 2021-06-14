package al.edu.cit.store.repositories;
import al.edu.cit.store.models.BookCollection;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;


public interface CollectionRepository extends JpaRepository<BookCollection, Long> {
    @Override
    List<BookCollection> findAll();

    @Override
    Optional<BookCollection> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    BookCollection save(BookCollection collection);
}
