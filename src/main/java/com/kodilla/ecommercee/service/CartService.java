package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;

    public List<Cart> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        new ArrayList<>(carts);
        return carts;
    }

    public Cart getCartById(Integer cartId) throws CartNotFoundException {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with id: " + cartId));
    }

    public Cart saveCart(Cart cart, CartDto cartDto) {

        Optional<User> user = userRepository.findById(cartDto.getUserId());
        if (user.isEmpty()) {
            throw new RuntimeException("User not found by id: " + cartDto.getUserId());
        }

        user.ifPresent(cart::setUser);

        return cartRepository.save(cart);
    }

    public CartDto updateCart(CartDto cartDto, int cartId) throws CartNotFoundException {
        Cart existingCart = getCartById(cartId);
        Cart updatedCart = cartMapper.mapToCart(cartDto);
        updatedCart.setCartId(existingCart.getCartId());

        Optional<User> user = userRepository.findById(cartDto.getUserId());
        if (user.isEmpty()) {
            throw new RuntimeException("User not found by id: " + cartDto.getUserId());
        }

        user.ifPresent(updatedCart::setUser);
        Cart cart = cartRepository.save(updatedCart);

        return cartMapper.mapToCartDto(cart);
    }

    public void deleteCart(Integer cartId) {
        cartRepository.deleteById(cartId);
    }
}