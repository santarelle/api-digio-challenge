package com.digio.challenge.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Log4j2
public final class ObjectMapperUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static <T> T map(TypeReference<T> reference, ResultActions resultActions) {
        try {
            String contentAsString = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
            return objectMapper.readValue(contentAsString, reference);
        } catch (UnsupportedEncodingException | JsonProcessingException ex) {
            log.error("Error to convert result actions to a reference {}", reference, ex);
            throw new RuntimeException(ex);
        }
    }
}
