package com.lucasambrosi.demo.application.controller;

import java.util.UUID;

public class User {
    private String id;
    private String name;
    private String taxId;

    public User(String name, String taxId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.taxId = taxId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTaxId() {
        return taxId;
    }
}
