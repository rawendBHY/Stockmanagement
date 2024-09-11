package com.example.stock.services;

import com.example.stock.models.Product;
import com.example.stock.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public int getTotalQuantity() {
        Integer quantity = productRepository.getTotalQuantity();
        return quantity != null ? quantity : 0;
    }
    

    @Override
    public List<Product> searchProducts(String keyword) {
        List<Product> products = productRepository.searchProducts(keyword);
        if (products.isEmpty()) {
            products = productRepository.findAll();
        }
        return products;
    }
    

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + id));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean existsByRef(String ref) {
        return productRepository.existsByRef(ref);
    }

    @Override
    public boolean existsByRefAndIdNot(String ref, Long id) {
        return productRepository.existsByRefAndIdNot(ref, id);
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
