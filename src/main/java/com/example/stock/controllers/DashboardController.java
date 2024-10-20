package com.example.stock.controllers;

import com.example.stock.models.ProductSor;
import com.example.stock.services.ProductService;
import com.example.stock.services.ProductSorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final ProductService productService;
    private final ProductSorService productSorService;

    public DashboardController(ProductService productService, ProductSorService productSorService) {
        this.productService = productService;
        this.productSorService = productSorService;
    }

    @GetMapping("/")
    public String index(Model model) {
        int totalQuantity = productService.getTotalQuantity();
        long totalProductSor = productSorService.countAll();
        ProductSor mostConsumedPiece = productSorService.findMostConsumedPiece().orElse(null);
        model.addAttribute("totalQuantity", totalQuantity);
        model.addAttribute("totalProductSor", totalProductSor);
        model.addAttribute("mostConsumedPiece", mostConsumedPiece);
        return "index";
    }
}
