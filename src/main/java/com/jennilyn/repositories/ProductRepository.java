package com.jennilyn.repositories;

import com.jennilyn.models.Product;
import com.jennilyn.models.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAllByProductNameContains(String query);
    Product findProductByProductName(String productName);
    List<Product> findAllBySupplier(Supplier supplier);

}
