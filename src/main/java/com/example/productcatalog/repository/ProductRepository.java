// src/main/java/com/example/productcatalog/repository/ProductRepository.java
package com.example.productcatalog.repository;

import com.example.productcatalog.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Marks this interface as a Spring Data repository
public interface ProductRepository extends MongoRepository<Product, String> {
    // MongoRepository provides standard CRUD operations (save, findById, findAll, deleteById, etc.)
    // You can define custom query methods here, Spring Data will implement them based on method name.
    // Example: Find products by name (case-insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Example: Find products by price less than a given value
    List<Product> findByPriceLessThan(double price);
}
