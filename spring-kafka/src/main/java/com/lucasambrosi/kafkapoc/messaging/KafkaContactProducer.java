package com.lucasambrosi.kafkapoc.messaging;

import com.lucasambrosi.kafkapoc.model.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaContactProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaContactProducer.class);
    @Autowired
    private KafkaTemplate<String, Contact> kafkaTemplate;

    @Value("${spring.kafka.topic.contact-creation}")
    private String contactCreationTopic;

    public void sendContactCreationMessageToKafka(Contact contact) {
        LOGGER.info("Sending message to TOPIC {} in Kafka. Data: {}", contactCreationTopic, contact.toString());
        this.kafkaTemplate.send(contactCreationTopic, contact);
    }
}
