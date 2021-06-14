package al.edu.cit.store.repositories;

import al.edu.cit.store.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Override
    List<Genre> findAll();
}
