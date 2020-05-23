package com.lucasambrosi.demo.application.controller;

import java.time.Month;
import java.util.UUID;

public class ResultComputation {
    private String id;
    private Month month;
    private Long currentCounter;

    public ResultComputation(Month month, Long currentCounter) {
        this.id = UUID.randomUUID().toString();
        this.month = month;
        this.currentCounter = currentCounter;
    }

    public String getId() {
        return id;
    }

    public Month getMonth() {
        return month;
    }

    public Long getCurrentCounter() {
        return currentCounter;
    }
}
