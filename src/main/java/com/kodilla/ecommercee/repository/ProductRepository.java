package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findAllByGroup(Group savedGroup);

    void deleteAllByGroup(Group savedGroup);
}
