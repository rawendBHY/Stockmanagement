package com.example.stock.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.stock.models.ProductSor;

public interface ProductSorService {
    String saveProductSor(ProductSor productSor);
    long countAll();
    List<ProductSor> searchProduct(String word);
    Map<String, List<ProductSor>> getPieces();
    Optional<ProductSor> findMostConsumedPiece();
    
}
