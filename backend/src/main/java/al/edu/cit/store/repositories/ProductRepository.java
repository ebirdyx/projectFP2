package al.edu.cit.store.repositories;

import al.edu.cit.store.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    List<Product> findAll(); // returns all products in the database

}
