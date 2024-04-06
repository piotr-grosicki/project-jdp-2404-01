package com.kodilla.ecommercee.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
public class CartController {
    private final List<String> cart = new ArrayList<>();

    @GetMapping("/getcart")
    public List<String> getCart() {
        return cart;
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody String Product) {
        cart.add(Product);
        return "Item added to cart";
    }

    @DeleteMapping("/remove")
    public String removeProduct(@RequestParam String Product) {
        if (cart.remove(Product)) {
            return "Item removed from cart";
        } else {
            return "Item not found in cart";
        }
    }

    @PostMapping("/order")
    public String createOrder() {
        if (cart.isEmpty()) {
            return "Cannot create order. Cart is empty.";
        } else {
            return "Order created";
        }
    }
}
