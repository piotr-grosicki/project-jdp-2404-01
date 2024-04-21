package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserService userService;
    private final CartMapper cartMapper;
    private final ProductService productService;

    public List<Cart> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        new ArrayList<>(carts);
        return carts;
    }

    public Cart getCartById(Integer cartId) throws CartNotFoundException {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with id: " + cartId));
    }

    public Cart saveCart(CartDto cartDto) throws UserNotFoundException {
        ;
        Cart cart = cartMapper.mapToCart(cartDto);
        cart.setUser(userService.getUser(cartDto.getUserId()));

        return cartRepository.save(cart);
    }

    public CartDto updateCart(CartDto cartDto, int cartId) throws CartNotFoundException, UserNotFoundException {

        if (!cartRepository.existsById(cartId)) {
            throw new CartNotFoundException(String.format("Cart with id %s not found", cartId));
        }

        cartDto.setCartId(cartId);
        Cart cart = cartMapper.mapToCart(cartDto);
        cart.setUser(userService.getUser(cartDto.getUserId()));

        return cartMapper.mapToCartDto(cartRepository.save(cart));
    }

    public void deleteCart(Integer cartId) {
        cartRepository.deleteById(cartId);
    }

    @Transactional
    public CartDto addProductToCart(int cartId, int productId) throws ProductNotFoundException, CartNotFoundException {
        Product product = productService.getProductById(productId);
        Cart cart = getCartById(cartId);
        cart.getProducts().add(product);

        return cartMapper.mapToCartDto(cartRepository.save(cart));
    }
}