package com.jennilyn.controllers;

import com.jennilyn.models.Product;
import com.jennilyn.models.Supplier;
import com.jennilyn.repositories.ProductRepository;
import com.jennilyn.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiProductController {

    @Autowired
    ProductRepository productRepo;

    @Autowired
    SupplierRepository supplierRepo;

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

    @RequestMapping(value = "/api/products/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Product addProduct(@RequestBody Product product) {
//        Supplier supplier = supplierRepo.findOne(supplierId);
//        Product product = new Product();
//        product.setProductName(product.productName);
//        product.setDescription(description);
//        product.setPurchaseCost(purchaseCost);
//        product.setSalePrice(salePrice);
//        product.setNumberInStock(numberInStock);
//        product.setSupplier(supplier);
        productRepo.save(product);
//        Product newProduct = productRepo.findProductByProductName(productName);

        return product;
    }

}
