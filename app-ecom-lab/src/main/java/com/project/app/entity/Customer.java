package com.project.app.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Customer() {}

    public Customer(String name) {
        this.name = name;
    }

    // getters and setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Order> getOrders() { return orders; }
    public void setName(String name) { this.name = name; }
    public void setOrders(List<Order> orders) { this.orders = orders; }
}
