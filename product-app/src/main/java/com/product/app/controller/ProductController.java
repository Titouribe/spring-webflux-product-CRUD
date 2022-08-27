package com.product.app.controller;

import com.product.app.model.entities.Product;
import com.product.app.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    private final String TABLE_TITLE = "Products List";
    private final String FORM_TITLE = "Product Form";

    @GetMapping
    public Mono<String> findAll(Model model) {

        model.addAttribute("products", productService.findAll());
        model.addAttribute("title", TABLE_TITLE);

        return Mono.just("productList");
    }

    @GetMapping("/{id}")
    public Mono<Product> findById(@PathVariable String id) {
        return productService.findById(id);
    }

    @GetMapping("/form")
    public Mono<String> createProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("title", FORM_TITLE);
        return Mono.just("formView");
    }


    @PostMapping("/form")
    public Mono<String> saveProduct(Product product){
        return productService.saveProduct(product)
                .thenReturn("redirect:/product");
    }
}
