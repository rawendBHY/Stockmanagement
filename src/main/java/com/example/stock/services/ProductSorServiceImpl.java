package com.example.stock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock.models.Product;
import com.example.stock.models.ProductSor;
import com.example.stock.repositories.ProductRepository;
import com.example.stock.repositories.ProductSorRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductSorServiceImpl implements ProductSorService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSorRepository productSorRepository;

    @Override
    public String saveProductSor(ProductSor productSor) {
        Optional<Product> optionalProduct = productRepository.findByRef(productSor.getRef());

        if (!optionalProduct.isPresent()) {
            return "Pièce introuvable";
        }

        Product product = optionalProduct.get();

        if (!product.getName().equals(productSor.getName())) {
            return "Le nom ne correspond pas à la référence";
        }

        if (product.getQt() > 0) {
            product.decrementQuantity(1);
            productRepository.save(product);
            productSorRepository.save(productSor);
            return null;
        } else {
            return "Quantité insuffisante pour la référence " + productSor.getRef();
        }
    }

    @Override
    public long countAll() {
        return productSorRepository.count();
    }

    @Override
    public List<ProductSor> searchProduct(String word) {
        List<ProductSor> productS = productSorRepository.searchProduct(word);
        if (productS.isEmpty()) {
            productS = productSorRepository.findAll();
        }
        return productS;
    }

    @Override
    public Map<String, List<ProductSor>> getPieces() {
        List<ProductSor> allProducts = productSorRepository.findAll();
        return allProducts.stream().collect(Collectors.groupingBy(product -> product.getNameMach() + " " + product.getRefMach()));
    }

    @Override
    public Optional<ProductSor> findMostConsumedPiece() {
        List<ProductSor> allProducts = productSorRepository.findAll();
        if (allProducts.isEmpty()) {
            return Optional.empty();
        }
        Map<ProductSor, Long> pieceCounts = allProducts.stream().collect(Collectors.groupingBy(product -> product, Collectors.counting()));
        return pieceCounts.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey);
    }
}

