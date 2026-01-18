package com.project.app.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    public Product() {}

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // getters and setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
}
