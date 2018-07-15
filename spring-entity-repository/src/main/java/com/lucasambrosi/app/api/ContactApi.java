package com.lucasambrosi.app.api;

import com.lucasambrosi.app.entity.ContactEntity;
import com.lucasambrosi.app.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class ContactApi {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contacts/{id}")
    @ResponseBody
    public String getById(@PathVariable(value = "id") Integer id){
        try {
            Optional<ContactEntity> contact = contactRepository.findById(id);
            if(contact.isPresent()) return contact.get().toString();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return "Contact not found!";
    }
}
