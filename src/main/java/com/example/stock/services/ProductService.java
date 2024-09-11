package com.example.stock.services;

import com.example.stock.models.Product;
import java.util.List;

public interface ProductService {
    int getTotalQuantity();
    List<Product> searchProducts(String keyword);
    void saveProduct(Product product);
    Product getProductById(Long id);
    void deleteProduct(Long id);
    boolean existsByRef(String ref);
    boolean existsByRefAndIdNot(String ref, Long id);
    List<Product> getAllProducts();
}
