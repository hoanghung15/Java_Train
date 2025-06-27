package org.example.ex1.Util;

public class ProductNotExist extends RuntimeException {
    public ProductNotExist(String message) {
        super(message);
    }
}
