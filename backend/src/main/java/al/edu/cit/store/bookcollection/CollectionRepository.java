package al.edu.cit.store.bookcollection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
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
