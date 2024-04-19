package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/carts")
public class CartController {

    private final CartMapper cartMapper;
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        List<CartDto> cartDtos = cartMapper.mapToCartDtoList(carts);
        return ResponseEntity.ok(cartDtos);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
        Cart savedCart = cartService.saveCart(cart);
        CartDto savedCartDto = cartMapper.mapToCartDto(savedCart);
        return ResponseEntity.ok(savedCartDto);
    }

    @DeleteMapping("{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable int cartId) {
        cartService.deleteCartById(cartId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable int cartId) {
        Cart cart = cartService.getCartById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found by id:" + cartId));
        CartDto cartDto = cartMapper.mapToCartDto(cart);
        return ResponseEntity.ok(cartDto);
    }

    @PutMapping("{cartId}")
    public ResponseEntity<CartDto> updateCart(@PathVariable int cartId, @RequestBody CartDto cartDto) {
        Cart existingCart = cartService.getCartById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found by id:" + cartId));

        Cart updatedCart = cartMapper.mapToCart(cartDto);
        updatedCart.setCartId(existingCart.getCartId());

        Cart savedCart = cartService.updateCart(updatedCart);
        CartDto savedCartDto = cartMapper.mapToCartDto(savedCart);
        return ResponseEntity.ok(savedCartDto);
    }
}
