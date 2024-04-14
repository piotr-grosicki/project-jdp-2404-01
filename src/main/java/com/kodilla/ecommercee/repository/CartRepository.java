package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {

    @Override
    List<Cart> findAll();

    @Override
    Cart save(Cart cart);

    @Override
    Optional<Cart> findById(Integer id);
}
