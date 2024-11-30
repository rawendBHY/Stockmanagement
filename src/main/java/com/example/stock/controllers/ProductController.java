package com.example.stock.controllers;

import com.example.stock.models.Product;
import com.example.stock.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/productlist")
    public String productList(Model model) {
        model.addAttribute("products", productService.searchProducts(""));
        return "productlist";
    }

    @GetMapping("/product/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "newProduct";
    }

    @PostMapping("/product")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newProduct";
        }
        if (productService.existsByRef(product.getRef())) {
            bindingResult.addError(new ObjectError("product", "La référence du produit existe déjà"));
            return "newProduct";
        }
        productService.saveProduct(product);
        return "redirect:/productlist";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/product/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editProduct";
        }

        if (productService.existsByRefAndIdNot(product.getRef(), id)) {
            bindingResult.addError(new ObjectError("product", "La référence du produit existe déjà"));
            return "editProduct";
        }
        productService.saveProduct(product);
        return "redirect:/productlist";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/productlist";
    }

    @GetMapping("/product/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.searchProducts(keyword);
        model.addAttribute("products", products);
        return "productlist";
    }
}
