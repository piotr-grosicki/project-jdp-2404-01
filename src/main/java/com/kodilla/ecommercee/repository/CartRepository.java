package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {

    List<Cart> findAllByUser(User user);

    @Override
    List<Cart> findAll();
}
