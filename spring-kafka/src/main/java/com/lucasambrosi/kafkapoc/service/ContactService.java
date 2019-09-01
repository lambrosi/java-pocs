package com.lucasambrosi.kafkapoc.service;

import com.lucasambrosi.kafkapoc.api.v1.input.ContactInput;
import com.lucasambrosi.kafkapoc.mapper.ContactMapper;
import com.lucasambrosi.kafkapoc.messaging.KafkaContactProducer;
import com.lucasambrosi.kafkapoc.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactService.class);

    @Autowired
    private KafkaContactProducer kafkaContactProducer;

    public Contact createContact(ContactInput input) {
        LOGGER.info("Creating contact...");
        Contact contact = ContactMapper.toContact(input);

        LOGGER.info("Sending contact ID {} to Kafka...", contact.getId());
        kafkaContactProducer.sendContactCreationMessageToKafka(contact);
        return contact;
    }
}
