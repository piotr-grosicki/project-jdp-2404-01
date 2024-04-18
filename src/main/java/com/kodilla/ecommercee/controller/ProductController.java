package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> productDtos = productMapper.mapToProductDtoList(productService.getAllProducts());
        return ResponseEntity.ok().body(productDtos);
    }

    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable int productId) {
        return productService.getProductById(productId)
                .map(product -> ResponseEntity.ok().body(productMapper.mapToProductDto(product)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productMapper.mapToProduct(productDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable int productId, @RequestBody ProductDto productDto) {
        productService.getProductById(productId).orElseThrow(() -> new RuntimeException("Product not found by id: " + productId));
        productDto.setProductId(productId);
        productService.saveProduct(productMapper.mapToProduct(productDto));
        return ResponseEntity.noContent().build();
    }
}
