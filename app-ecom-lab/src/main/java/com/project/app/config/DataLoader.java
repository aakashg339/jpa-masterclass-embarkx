package com.project.app.config;

import com.project.app.entity.Customer;
import com.project.app.entity.Order;
import com.project.app.entity.Product;
import com.project.app.repository.CustomerRepository;
import com.project.app.repository.OrderRepository;
import com.project.app.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(CustomerRepository customerRepo,
                               ProductRepository productRepo,
                               OrderRepository orderRepo) {
        return args -> {
            // ----- Seed Data (save and keep references to managed entities) -----
            Product p1 = productRepo.save(new Product("Laptop", 1200));
            Product p2 = productRepo.save(new Product("Phone", 800));
            Product p3 = productRepo.save(new Product("Headphones", 150));

            Customer c1 = customerRepo.save(new Customer("Alice"));
            Customer c2 = customerRepo.save(new Customer("Bob"));

            Order o1 = new Order(LocalDate.now().minusDays(5), "PENDING", c1, List.of(p1, p3));
            Order o2 = new Order(LocalDate.now().minusDays(10), "DELIVERED", c1, List.of(p2));
            Order o3 = new Order(LocalDate.now().minusDays(40), "PENDING", c2, List.of(p1, p2));
            orderRepo.saveAll(List.of(o1, o2, o3));

            System.out.println("Seed data inserted.");

            // ---------------- JPQL TASK VALIDATION (uncomment one task at a time) ----------------

            // Task — High-Value Orders
//            System.out.println("\n--- Task : High-Value Orders (total > 1000) ---");
//            List<Object[]> high = orderRepo.findHighValueOrders(1000.0);
//            high.forEach(r -> {
//                Number id = (Number) r[0];
//                Number total = (Number) r[1];
//                System.out.println("Order ID: " + id.longValue() + ", Total: " + total.doubleValue());
//            });

            // Task — Customer Purchase History
//            System.out.println("\n--- Task : Orders for Customer (most recent first) ---");
//            List<Order> customerOrders = orderRepo.findOrdersByCustomerIdSorted(c1.getId());
//            customerOrders.forEach(o -> System.out.println("Order ID: " + o.getId() + ", Date: " + o.getOrderDate() + ", Status: " + o.getStatus()));

            // Task — Top-Selling Products (top 3)
//            System.out.println("\n--- Task : Top-Selling Products (top 3) ---");
//            List<Object[]> top = productRepo.findTopSellingProducts(PageRequest.of(0, 3));
//            top.forEach(r -> {
//                String productName = (String) r[0];
//                Number units = (Number) r[1]; // number of orders containing the product
//                System.out.println("Product: " + productName + ", Units Sold (orders): " + units.longValue());
//            });

            // Task — Revenue by Customer
//            System.out.println("\n--- Task : Revenue by Customer ---");
//            List<Object[]> revenue = orderRepo.calculateRevenueByCustomer();
//            revenue.forEach(r -> {
//                String customerName = (String) r[0];
//                Number total = (Number) r[1];
//                System.out.println("Customer: " + customerName + ", Total Revenue: " + total.doubleValue());
//            });

            // Task — Cancel Stale Pending Orders
//            System.out.println("\n--- Task : Cancel Stale Pending Orders ---");
//            int updated = orderRepo.cancelStalePendingOrders(LocalDate.now().minusDays(30));
//            System.out.println("Orders updated (set to CANCELLED): " + updated);
        };
    }
}
