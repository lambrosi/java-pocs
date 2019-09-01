package com.lucasambrosi.kafkapoc.api.v1;

import com.lucasambrosi.kafkapoc.api.v1.input.ContactInput;
import com.lucasambrosi.kafkapoc.api.v1.output.ContactOutput;
import com.lucasambrosi.kafkapoc.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/contact")
public class ContacApi {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactOutput> createContact(@RequestBody ContactInput input) {
        return ResponseEntity.ok(new ContactOutput(contactService.createContact(input).getId()));
    }
}
