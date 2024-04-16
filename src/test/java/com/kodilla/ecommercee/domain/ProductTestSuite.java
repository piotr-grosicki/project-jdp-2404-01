package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductTestSuite {


    @Autowired
    ProductRepository productRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    CartRepository cartRepository;



    @Test
    public void testCreateProduct() {
        // Given
        List<Product> groupedProducts = new ArrayList<>();
        Group group = new Group(1, "Description", groupedProducts);
        Product product = new Product(1, "Test Product", BigDecimal.valueOf(15.00), group, null, null);
        //When
        Group savedGroup = groupRepository.save(group);
        Product savedProduct = productRepository.save(product);
        //Then
        assertNotNull(savedGroup);
        assertEquals("Test Product", savedProduct.getName());
        assertEquals(BigDecimal.valueOf(15.00), savedProduct.getPrice());
        assertEquals(1, savedProduct.getGroup().getGroupId());
    }

    @Test
    public void testFindProductById() {
        // Given
        List<Product> groupedProducts = new ArrayList<>();
        Group group = new Group(1, "Description", groupedProducts);
        Product product = new Product(2, "Test Product", BigDecimal.valueOf(15.00), group, null, null);
        groupRepository.save(group);
        product = productRepository.save(product);
        // When
        Optional<Product> retrievedProduct = productRepository.findById(product.getProductId());
        // Then
        assertNotNull(retrievedProduct);
        assertEquals(2, retrievedProduct.get().getProductId());
        assertEquals("Test Product", retrievedProduct.get().getName());
        assertEquals(BigDecimal.valueOf(15.00).setScale(2, RoundingMode.CEILING), retrievedProduct.get().getPrice());
        assertEquals(2, group.getGroupId());
    }

    @Test
    public void testDeleteProduct() {
        // Given
        List<Product> groupedProducts = new ArrayList<>();
        Group group = new Group(3, "Description", groupedProducts);
        Product product = new Product(2, "Test Product", BigDecimal.valueOf(15.00), group, null, null);
        groupRepository.save(group);
        productRepository.save(product);
        // When
        productRepository.deleteById(product.getProductId());
        Optional<Product> deletedProduct = productRepository.findById(product.getProductId());
        List<Group> groups = groupRepository.findAll();
        // Then
        Assertions.assertFalse(deletedProduct.isPresent());
        assertEquals(1, groups.size());
    }

    @Test
    public void testUpdateProduct() {
        // Given
        List<Product> groupedProducts = new ArrayList<>();
        Group group = new Group(4, "Description", groupedProducts);
        Product product = new Product(3, "Test Product", BigDecimal.valueOf(15.00), group, null, null);
        groupRepository.save(group);
        productRepository.save(product);
        // When
        product.setName("Name after update");
        product.setPrice(BigDecimal.valueOf(10.00));
        Optional<Product> updatedProduct = productRepository.findById(product.getProductId());
        // Then
        assertNotNull(updatedProduct);
        assertEquals(3, product.getProductId());
        assertEquals("Name after update", product.getName());
        assertEquals(BigDecimal.valueOf(10.00), product.getPrice());
    }

    @Test
    public void testFindAllProducts() {
        // Given
        List<Product> groupedProducts = new ArrayList<>();
        Product product1 = new Product(1, "Test Product1", BigDecimal.valueOf(15.00).setScale(2, RoundingMode.CEILING), null, null, null);
        Product product2 = new Product(2, "Test Product2", BigDecimal.valueOf(15.00).setScale(2, RoundingMode.CEILING), null, null, null);
        productRepository.save(product1);
        productRepository.save(product2);
        //When
        List<Product> retrievedProducts = productRepository.findAll();
        //Then
        assertEquals(2, retrievedProducts.size());
        assertEquals("Test Product1", retrievedProducts.get(0).getName());
        assertEquals("Test Product2", retrievedProducts.get(1).getName());
    }
    @AfterEach
    public void cleanup() {
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }
}
