package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    @Transactional
    public void testDeleteGroup() {
        // GIVEN
        Group group = new Group();
        group.setDescription("Test Group");
        Group savedGroup = groupRepository.save(group);

        Product product = new Product();
        product.setName("Test Product");
        product.setGroup(savedGroup);
        productRepository.save(product);

        // WHEN
        productRepository.deleteAllByGroup(savedGroup);
        groupRepository.delete(savedGroup);

        // THEN
        assertFalse(groupRepository.findById(savedGroup.getGroupId()).isPresent());
        assertTrue(productRepository.findAllByGroup(savedGroup).isEmpty());
    }
}
