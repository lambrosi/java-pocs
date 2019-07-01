package com.lucasambrosi.app.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasambrosi.app.model.Contato;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Qualifier("contatoProcessor")
@Service
public class ContatoProcessor implements ItemProcessor<Map<Object, Object>, Contato> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Contato process(Map<Object, Object> item) throws Exception {
        return objectMapper.convertValue(item, Contato.class);
    }
}
