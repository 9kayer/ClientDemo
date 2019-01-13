package com.example.clientdemo.infrastructure.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String id) {
        super(id);
    }
}
