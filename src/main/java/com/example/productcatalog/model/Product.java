// src/main/java/com/example/productcatalog/model/Product.java
package com.example.productcatalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields
@Document(collection = "products") // Maps this class to the "products" collection in MongoDB
public class Product {
    @Id // Marks this field as the primary identifier for the document
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;

}