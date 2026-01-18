package com.project.app.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders") // 'order' is reserved in SQL
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
        name = "order_products",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Order() {}

    public Order(LocalDate orderDate, String status, Customer customer, List<Product> products) {
        this.orderDate = orderDate;
        this.status = status;
        this.customer = customer;
        this.products = products;
    }

    // getters and setters
    public Long getId() { return id; }
    public LocalDate getOrderDate() { return orderDate; }
    public String getStatus() { return status; }
    public Customer getCustomer() { return customer; }
    public List<Product> getProducts() { return products; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    public void setStatus(String status) { this.status = status; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setProducts(List<Product> products) { this.products = products; }
}
