package al.edu.cit.store.services;

import al.edu.cit.store.exceptions.ProductNotFoundException;
import al.edu.cit.store.models.Product;
import al.edu.cit.store.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException();
        }
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ProductNotFoundException();
        }
    }

    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> oldProduct = productRepository.findById(id);
        if (oldProduct.isPresent()) {
            Product old = oldProduct.get();

            old.setName(product.getName());
            old.setDescription(product.getDescription());
            old.setPrice(product.getPrice());

            return productRepository.save(old);
        } else {
            throw new ProductNotFoundException();
        }
    }
}
