// src/main/java/com/example/productcatalog/service/ProductService.java
package com.example.productcatalog.service;

import com.example.productcatalog.model.Product;
import com.example.productcatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marks this class as a Spring service component
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired // Injects ProductRepository instance
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Create a new product
    public Product createProduct(Product product) {
        // Here you can add business logic, validation, etc. before saving
        return productRepository.save(product);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    // Update an existing product
    public Product updateProduct(String id, Product productDetails) {
        // Check if product exists before updating
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(productDetails.getName());
            existingProduct.setDescription(productDetails.getDescription());
            existingProduct.setPrice(productDetails.getPrice());
            existingProduct.setQuantity(productDetails.getQuantity());
            // Save the updated product
            return productRepository.save(existingProduct);
        } else {
            // Handle case where product is not found (e.g., throw an exception)
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    // Delete a product by ID
    public void deleteProduct(String id) {
        // Add checks or business logic before deleting
        productRepository.deleteById(id);
    }

    // Custom service methods using repository's custom queries
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getProductsCheaperThan(double price) {
        return productRepository.findByPriceLessThan(price);
    }
}
