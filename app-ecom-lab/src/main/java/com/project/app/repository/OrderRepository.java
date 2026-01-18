package com.project.app.repository;

import com.project.app.entity.Order;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o.id, SUM(p.price) FROM Order o JOIN o.products p GROUP BY o.id HAVING SUM(p.price) > :amount")
    List<Object[]> findHighValueOrders(double amount);

    @Query("SELECT o FROM Order o WHERE o.customer.id = ?1 ORDER BY o.orderDate DESC")
    List<Order> findOrdersByCustomerIdSorted(Long customerId);

    @Query("SELECT o.customer.name, SUM(p.price) FROM Order o JOIN o.products p GROUP BY o.customer.id, o.customer.name")
    List<Object[]> calculateRevenueByCustomer();

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.status = 'CANCELLED' WHERE o.orderDate < ?1")
    int cancelStalePendingOrders(LocalDate cutOffDate);

}
