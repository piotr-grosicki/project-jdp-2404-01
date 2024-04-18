package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> getProductById(int productId) {
        return productRepository.findById(productId);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(int productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        productOptional.ifPresentOrElse(
                product -> productRepository.deleteById(productId),
                () -> {
                    throw new IllegalArgumentException("Product with id " + productId + " does not exist");
                }
        );
    }
}
