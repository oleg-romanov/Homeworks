package ru.itis.repositories.productRepository;

import ru.itis.model.Category;
import ru.itis.model.Product;
import ru.itis.repositories.CrudRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product> {
    List<Product> findProductsByCategoryId(Long categoryId);
    Optional<Product> findProductById(Long id);
    Boolean deleteProductById(Long id);
    void updateProduct(Long id, Product product);
}
