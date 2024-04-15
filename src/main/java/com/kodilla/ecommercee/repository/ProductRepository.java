package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Override
    List<Product> findAll();

    Optional<Object> findAllByGroup(Group savedGroup);

    void deleteAllByGroup(Group savedGroup);
}
