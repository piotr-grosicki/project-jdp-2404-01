package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @GetMapping("/{cartId}")
    public CartDto getCart(@PathVariable int cartId) {
        return new CartDto(13, 5, Arrays.asList("Milk","Egg","Flour"));
    }

    @PutMapping("/add")
    public String addProduct(@RequestParam int cart, @RequestParam int productId) {
        return "Item added to cart";
    }

    @DeleteMapping("/remove")
    public String removeProduct(@RequestParam int cart, @RequestParam int productId) {
        return "Item removed from cart";
    }

    @PostMapping("/{cartId}")
    public String createOrder(@PathVariable int cartId) {
        return "Order created";
    }
}

