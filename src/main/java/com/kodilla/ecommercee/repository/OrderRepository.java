package com.kodilla.ecommercee.repository;


import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Override
    List<Order> findAll();

    @Override
    Order save(Order order);

    @Override
    Optional<Order> findById(Integer id);
}
