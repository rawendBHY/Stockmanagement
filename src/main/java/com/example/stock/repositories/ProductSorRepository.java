package com.example.stock.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.stock.models.ProductSor;

public interface ProductSorRepository extends JpaRepository<ProductSor, Long> {
    long count();

    @Query("SELECT pr FROM ProductSor pr WHERE pr.name LIKE %:word% OR pr.ref LIKE %:word%")
    List<ProductSor> searchProduct(@Param("word") String word);

}
