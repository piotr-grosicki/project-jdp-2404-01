package com.kodilla.ecommercee.exceptions;

public class CartNotFoundException extends Exception{
    public CartNotFoundException(String message) {
        super(message);
    }
}
