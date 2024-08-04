package com.digio.challenge.fixture;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.mockito.internal.util.Platform;

import java.io.IOException;
import java.io.InputStream;

@Log4j2
public class ResourceFixture {

    private ResourceFixture() {
    }

    public static String loadJson(String fileName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = ResourceFixture.class.getResourceAsStream("/fixtures/%s".formatted(fileName));
            if (inputStream == null || inputStream.available() == 0) {
                return null;
            }
            JsonNode jsonNode = objectMapper.readValue(inputStream, JsonNode.class);
            String json = objectMapper.writeValueAsString(jsonNode);
            if (!Platform.OS_NAME.contains("Windows")) {
                json = json.replace("\\r\\n", "\\n");
            }
            return json;
        } catch (IOException ex) {
            throw new RuntimeException("File not found or invalid. %s".formatted(fileName));
        }
    }
}
