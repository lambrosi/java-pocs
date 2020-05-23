package com.lucasambrosi.demo.application.exception;

public class ValueNotAllowedException extends RuntimeException {
    public ValueNotAllowedException() {
        super("Informed value not allowed. Must be an EVEN number.");
    }
}
