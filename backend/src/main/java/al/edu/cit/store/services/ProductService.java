package al.edu.cit.store.services;

import al.edu.cit.store.models.Product;
import al.edu.cit.store.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProdutcs() {
        return productRepository.findAll();
    }

}
