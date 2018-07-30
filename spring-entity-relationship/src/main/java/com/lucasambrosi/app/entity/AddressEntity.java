package com.lucasambrosi.app.entity;

import javax.persistence.*;

@Entity(name = "address_table")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String street;

    @Column(name = "num")
    private int number;

    @Column
    private String country;

    public AddressEntity() {}

    public AddressEntity(Integer id, String street, int number, String country) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
