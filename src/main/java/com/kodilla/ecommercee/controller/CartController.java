package com.kodilla.ecommercee.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
public class CartController {
    private final List<String> cartDto = new ArrayList<>();

    @GetMapping("/{cartId}")
    public List<String> getCart(@PathVariable String cartId) {
        return cartDto;
    }

    @PutMapping("/add")
    public String addProduct(@RequestParam int cart, @RequestBody String Product) {
        return "Item added to cart";
    }

    @DeleteMapping("/remove")
    public String removeProduct(@RequestParam int cart, @RequestParam String Product) {
        return "Item removed from cart";
    }


    @PostMapping("/{cartId}")
    public String createOrder() {
        if (cartDto.isEmpty()) {
            return "Cannot create order. Cart is empty.";
        } else {
            return "Order created";
        }
    }
}
