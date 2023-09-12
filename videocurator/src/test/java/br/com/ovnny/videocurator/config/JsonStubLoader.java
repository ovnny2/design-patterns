package br.com.ovnny.videocurator.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class JsonStubLoader<T> {
    private final ObjectMapper objectMapper;
    private final Class<T> type;

    public JsonStubLoader(ObjectMapper objectMapper, Class<T> type) {
        this.objectMapper = objectMapper;
        this.type = type;
    }

    public T load(String filePath) throws IOException {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(filePath);

        objectMapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);

        if (resourceAsStream == null) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        return objectMapper.readValue(resourceAsStream, type);
    }
}