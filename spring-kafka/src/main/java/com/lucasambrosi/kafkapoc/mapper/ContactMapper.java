package com.lucasambrosi.kafkapoc.mapper;

import com.lucasambrosi.kafkapoc.api.v1.input.ContactInput;
import com.lucasambrosi.kafkapoc.model.Contact;

import java.util.Random;

public class ContactMapper {

    public static Contact toContact(ContactInput input) {
        Contact c = new Contact();
        c.setId(generateId());
        c.setName(input.getName());
        c.setCpf(input.getCpf());
        c.setActive(input.getActive());
        return c;
    }

    private static Long generateId() {
        return new Random().nextLong();
    }
}
