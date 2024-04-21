package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
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
    public ResponseEntity<List<CartDto>> getCarts() {
        return ResponseEntity.ok(cartMapper.mapToCartDtoList(cartService.getAllCarts()));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartById(@PathVariable int cartId) throws CartNotFoundException {
        return ResponseEntity.ok(cartMapper.mapToCartDto(cartService.getCartById(cartId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto) throws UserNotFoundException {
        return ResponseEntity.ok().body(cartMapper.mapToCartDto(cartService.saveCart(cartDto)));
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable int cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<CartDto> updateCart(@PathVariable int cartId, @RequestBody CartDto cartDto)
            throws CartNotFoundException, UserNotFoundException {
        return ResponseEntity.ok(cartService.updateCart(cartDto, cartId));
    }

    @PostMapping("/{cartId}")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable int cartId, @RequestParam int productId)
            throws CartNotFoundException, ProductNotFoundException {
        return ResponseEntity.ok(cartService.addProductToCart(cartId, productId));
    }
}
