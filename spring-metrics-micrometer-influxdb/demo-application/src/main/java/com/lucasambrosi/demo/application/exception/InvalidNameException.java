package com.lucasambrosi.demo.application.exception;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException() {
        super("The user name must be fully informed.");
    }
}
