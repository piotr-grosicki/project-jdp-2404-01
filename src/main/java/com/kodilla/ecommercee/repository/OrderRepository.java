package com.kodilla.ecommercee.repository;


import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findAllByUser(User user);

    @Override
    List<Order> findAll();

}
