package com.lucasambrosi.demo.application.exception;

public class TaxIdNotAllowedException extends RuntimeException {
    public TaxIdNotAllowedException() {
        super("Informed taxId not allowed.");
    }
}
