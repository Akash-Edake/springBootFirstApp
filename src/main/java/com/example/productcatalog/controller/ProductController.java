// src/main/java/com/example/productcatalog/controller/ProductController.java
package com.example.productcatalog.controller;

import com.example.productcatalog.model.Product;
import com.example.productcatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST controller
@RequestMapping("/api/products") // Base path for all endpoints in this controller
public class ProductController {

    private final ProductService productService;

    @Autowired // Injects ProductService instance
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Endpoint to create a new product
    // POST /api/products
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED); // Returns 201 Created
    }

    // Endpoint to get all products
    // GET /api/products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK); // Returns 200 OK
    }

    // Endpoint to get a product by ID
    // GET /api/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return productService.getProductById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK)) // Returns 200 OK if found
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Returns 404 Not Found if not found
    }

    // Endpoint to update an existing product
    // PUT /api/products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        try {
            Product updatedProduct = productService.updateProduct(id, product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK); // Returns 200 OK
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Returns 404 Not Found if product not found
        }
    }

    // Endpoint to delete a product by ID
    // DELETE /api/products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns 204 No Content
    }

    // Endpoint to search products by name
    // GET /api/products/search?name=keyword
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String name) {
        List<Product> products = productService.searchProductsByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Endpoint to get products cheaper than a certain price
    // GET /api/products/cheaper-than?price=100.0
    @GetMapping("/cheaper-than")
    public ResponseEntity<List<Product>> getProductsCheaperThan(@RequestParam double price) {
        List<Product> products = productService.getProductsCheaperThan(price);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}