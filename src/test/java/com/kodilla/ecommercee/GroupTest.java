package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GroupTest {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCreateGroup() {
        // GIVEN
        Group group = new Group();
        group.setDescription("Test Group");

        // WHEN
        Group savedGroup = groupRepository.save(group);

        // THEN
        assertNotNull(savedGroup);
        assertEquals("Test Group", savedGroup.getDescription());
    }

    @Test
    public void testReadGroup() {
        // GIVEN
        Group group = new Group();
        group.setDescription("Test Group");
        Group savedGroup = groupRepository.save(group);

        // WHEN
        Optional<Group> foundGroupOptional = groupRepository.findById(savedGroup.getGroupId());

        // THEN
        assertTrue(foundGroupOptional.isPresent());
        Group foundGroup = foundGroupOptional.get();
        assertEquals("Test Group", foundGroup.getDescription());
    }
    @Test
    public void testUpdateGroup() {
        // GIVEN
        Group group = new Group();
        group.setDescription("Test Group");
        Group savedGroup = groupRepository.save(group);

        // WHEN
        savedGroup.setDescription("Updated Group");
        groupRepository.save(savedGroup);
        Optional<Group> updatedGroupOptional = groupRepository.findById(savedGroup.getGroupId());

        // THEN
        assertTrue(updatedGroupOptional.isPresent());
        Group updatedGroup = updatedGroupOptional.get();
        assertEquals("Updated Group", updatedGroup.getDescription());
    }
//    @Test
//    public void testDeleteProduct() {
//        // Given
//        Group group = new Group();
//        group.setDescription("Test Group");
//        Group savedGroup = groupRepository.save(group);
//
//        Product product = new Product();
//        product.setName("Test Product");
//        product.setPrice(BigDecimal.valueOf(15.00));
//        product.setGroup(savedGroup);
//        Product savedProduct = productRepository.save(product);
//
//        // When
//        productRepository.deleteById(savedProduct.getProductId());
//        Optional<Product> deletedProduct = productRepository.findById(savedProduct.getProductId());
//
//        // Then
//        assertFalse(deletedProduct.isPresent());
//    }

    @AfterEach
    public void cleanup() {
        productRepository.deleteAll();
        groupRepository.deleteAll();

    }
}
