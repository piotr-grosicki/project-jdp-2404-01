package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Product mapToProduct(ProductDto productDto) {
        return new Product(
                productDto.getProductId(),
                productDto.getName(),
                productDto.getPrice(),
                null,
                null,
                null
        );
    }

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getGroup() != null ? product.getGroup().getGroupId() : 0
        );
    }

    public List<ProductDto> mapToProductDtoList(List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
