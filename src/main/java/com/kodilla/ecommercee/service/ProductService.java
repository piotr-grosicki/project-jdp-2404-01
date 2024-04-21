package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final GroupService groupService;
    private final ProductMapper productMapper;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int productId) throws ProductNotFoundException {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with id %s not found", productId)));
    }

    public Product updateProduct(ProductDto productDto, int productId) throws ProductNotFoundException, GroupNotFoundException {
        if (productRepository.existsById(productId)) {
            productDto.setProductId(productId);
            return saveProduct(productDto);
        } else {
            throw new ProductNotFoundException("Product not found");
        }

    }

    public Product saveProduct(ProductDto productDto) throws GroupNotFoundException {
        Product product = productMapper.mapToProduct(productDto);

        Group group = groupService.getGroupById(productDto.getGroupId());
        product.setGroup(group);

        return productRepository.save(product);
    }

    public void deleteProductById(int productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with id %s not found", productId)));

        productRepository.delete(product);
    }
}
