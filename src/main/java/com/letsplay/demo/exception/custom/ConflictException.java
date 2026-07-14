package com.letsplay.demo.exception.custom;

public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
