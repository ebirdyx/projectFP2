package al.edu.cit.store.repositories;

import al.edu.cit.store.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    Product save(Product product);
}
