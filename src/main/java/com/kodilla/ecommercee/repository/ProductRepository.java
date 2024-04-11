package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {
}


