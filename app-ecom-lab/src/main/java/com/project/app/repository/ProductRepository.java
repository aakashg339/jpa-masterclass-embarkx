package com.project.app.repository;

import com.project.app.entity.Product;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p.name, COUNT(o) FROM Product p JOIN p.orders o GROUP BY p.name ORDER BY COUNT(o) DESC")
    List<Object[]> findTopSellingProducts(PageRequest of);
}
