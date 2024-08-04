package com.digio.challenge.cucumber;

import com.digio.challenge.ApiDigioChallengeApplication;
import com.digio.challenge.cucumber.stepdefs.StepDefs;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

@ActiveProfiles("test")
@WebAppConfiguration
@CucumberContextConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = ApiDigioChallengeApplication.class)
@ContextConfiguration(classes = {ApiDigioChallengeApplication.class})
public class SpringContextStepDefs extends StepDefs {

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void initializeContext() {
        super.initializeContext();
    }

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object defaultTransformer(Object value, Type type) {
        final Object handledValue = handleEmptyValues(value);
        final JavaType javaType = objectMapper.constructType(type);
        return objectMapper.convertValue(handledValue, javaType);
    }

    private Object handleEmptyValues(Object value) {
        if (value instanceof Map) {
            Map<Object, Object> handledMap = new LinkedHashMap<>();
            for (Object o : ((Map<?, ?>) value).entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Object handledValue = handleEmptyValue(entry.getValue());
                entry.setValue(handledValue);
                handledMap.put(entry.getKey(), entry.getValue());
            }
            return handledMap;
        }

        return handleEmptyValue(value);
    }

    private Object handleEmptyValue(Object o) {
        if (o instanceof String && "<empty>".equalsIgnoreCase((String) o)) {
            return "";
        }
        return o;
    }
}
