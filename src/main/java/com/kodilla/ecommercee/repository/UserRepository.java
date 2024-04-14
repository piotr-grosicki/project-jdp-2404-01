package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    @Override
    List<User> findAll();
}
