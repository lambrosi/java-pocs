package com.lucasambrosi.app.entity;

import javax.persistence.*;

@Entity(name="contacts")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String personName;

    @Column
    private String email;

    @Column
    private String phone;

    public ContactEntity(){}

    public ContactEntity(int id, String personName, String email, String phone) {
        this.id = id;
        this.personName = personName;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("ID......: %d\n" +
                             "Nome....: %s\n" +
                             "Email...: %s\n" +
                             "Telefone: %s",
                             getId(), getPersonName(), getEmail(), getPhone());
    }
}
