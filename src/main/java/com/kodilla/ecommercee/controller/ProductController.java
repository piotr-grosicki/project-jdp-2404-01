package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @GetMapping
    public List<ProductDto> getProducts() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{productId}")
    public ProductDto getProduct(@PathVariable int productId) {
        return new ProductDto(1, "test", BigDecimal.valueOf(10.0), 5);
    }

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto) {
    }

    @DeleteMapping
    public void deleteProduct(@PathVariable int productId) {
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return new ProductDto(2, "test", BigDecimal.valueOf(10.0), 5);
    }
}
