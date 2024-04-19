package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {


    public Cart mapToCart(CartDto cartDto) {
        return new Cart(
                cartDto.getCartId(),
                null,
                null);
    }

    public CartDto mapToCartDto(Cart cart) {
        return new CartDto(
                cart.getCartId(),
                cart.getUser().getUserId(),
                cart.getProducts()
        );
    }

    public List<CartDto> mapToCartDtoList(List<Cart> cartList) {
        return cartList.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }
}
