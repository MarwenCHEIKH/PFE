package com.example.Emaildemo.service;

import com.example.Emaildemo.resource.EmailMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToEmailConverter implements Converter<String, EmailMessage> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public EmailMessage convert(String source) {
        return objectMapper.readValue(source, EmailMessage.class);
    }
}
