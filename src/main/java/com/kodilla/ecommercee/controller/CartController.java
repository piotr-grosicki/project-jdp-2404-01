package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.CartDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/carts")
public class CartController {

    @GetMapping("/{cartId}")
    public CartDto getCart(@PathVariable int cartId) {

        List<Product> products = new ArrayList<>();
        return new CartDto(13, 5, products);
    }

    @PutMapping
    public String addProduct(@RequestParam int cart, @RequestParam int productId) {
        return "Item added to cart";
    }

    @DeleteMapping("{cartId}")
    public void removeProduct(@PathVariable int cartId) {
    }

    @PostMapping("/{cartId}")
    public String createOrder(@PathVariable int cartId) {
        return "Order created";
        }
    }

