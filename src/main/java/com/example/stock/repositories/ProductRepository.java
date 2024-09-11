package com.example.stock.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.stock.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long > {
    boolean existsByRef(String ref);
    boolean existsByRefAndIdNot(String ref, Long id);
    Optional<Product> findByRef(String ref);

    @Query("SELECT SUM(p.qt) FROM Product p")
    Integer getTotalQuantity();

    List<Product> findByNameOrRef(String name, String ref);
    
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR p.ref LIKE %:keyword%")
    List<Product> searchProducts(@Param("keyword") String keyword);
}
