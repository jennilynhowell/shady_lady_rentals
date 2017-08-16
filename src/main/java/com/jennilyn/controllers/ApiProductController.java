package com.jennilyn.controllers;

import com.jennilyn.models.Product;
import com.jennilyn.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiProductController {

    @Autowired
    ProductRepository productRepo;

    @RequestMapping("/api/products")
    public Iterable<Product> productApiList() { return productRepo.findAll(); }

    @RequestMapping("/api/products/{productId}")
    public Product productApiById(@PathVariable("productId") Long productId) {
        return productRepo.findOne(productId);
    }

    @RequestMapping("/api/products/")
    public Iterable<Product> productApiSearchList(@RequestParam(value = "search", defaultValue = "canopy") String query) {
        return productRepo.findAllByProductNameContains(query);
    }

}
