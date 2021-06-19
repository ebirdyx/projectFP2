package io.kyorg.springsecuritydemo.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    List<Book> findAll();

    @Override
    Optional<Book> findById(Long id);

    @Override
    void deleteById(Long id);
}
