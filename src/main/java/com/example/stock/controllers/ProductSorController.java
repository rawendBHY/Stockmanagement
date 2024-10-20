package com.example.stock.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.stock.models.Product;
import com.example.stock.models.ProductSor;
import com.example.stock.repositories.ProductSorRepository;
import com.example.stock.services.ProductService;
import com.example.stock.services.ProductSorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductSorController {

    private final ProductSorRepository productSorRepository;
    private final ProductSorService productSorService;

    private final ProductService productService;

    @GetMapping("/productSor")
    public String productSor(Model model) {
        model.addAttribute("productS", productSorRepository.findAll());
        return "productSor";
    }

    @GetMapping("/productsor/new")
    public String newProductSor(Model model) {
        model.addAttribute("productSor", new ProductSor());
        return "mouvement";
    }

    @PostMapping("/productsor/new")
    public String addProductSor(@Valid @ModelAttribute ProductSor productSor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "mouvement";
        }

        String errorMessage = productSorService.saveProductSor(productSor);
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            return "mouvement";
        }

        return "redirect:/productSor";
    }

    @GetMapping("/productsor/edit/{id}")
    public String editProductSor(@PathVariable Long id, Model model) {
        ProductSor productSor = productSorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid productSor Id:" + id));
        model.addAttribute("productSor", productSor);
        return "editProductSor";  
    }

    @PostMapping("/productsor/edit/{id}")
    public String updateProductSor(@PathVariable Long id, @ModelAttribute ProductSor productSor, Model model) {
        productSor.setId(id); 
        String errorMessage = productSorService.saveProductSor(productSor);
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            return "editProductSor";
        }
        return "redirect:/productSor";
    }

    @GetMapping("/productsor/delete/{id}")
    public String deleteProductSor(@PathVariable Long id){
        productSorRepository.deleteById(id);
        return "redirect:/productSor";
    }

    @GetMapping("/productsor/search")
    public String searchProduct(@RequestParam("word") String word, Model model) {
        List<ProductSor> productS = productSorService.searchProduct(word);
        model.addAttribute("productS", productS);
        return "productSor";
    }

@GetMapping("/machtable")
public String showMachines(Model model) {
    List<Product> products = productService.getAllProducts();
    Map<String, List<ProductSor>> pieces = productSorService.getPieces();
    List<Map.Entry<String, List<ProductSor>>> Machines = new ArrayList<>(pieces.entrySet());
    Machines.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));
    model.addAttribute("Machines", Machines);
    model.addAttribute("products", products);
    return "machtable"; 
}
}
