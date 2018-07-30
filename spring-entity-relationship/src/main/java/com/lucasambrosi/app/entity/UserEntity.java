package com.lucasambrosi.app.entity;

import javax.persistence.*;

@Entity(name="users_table")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String personName;

    @Column
    private String phone;

    @ManyToOne
    private AddressEntity address;

    public UserEntity(){}

    public UserEntity(Integer id, String personName, String phone, AddressEntity address) {
        this.id = id;
        this.personName = personName;
        this.phone = phone;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("ID......: %d\n" +
                             "Nome....: %s\n" +
                             "Telefone: %s",
                             getId(), getPersonName(), getPhone());
    }
}
