package br.com.ovnny.videocurator.config;

import com.fasterxml.jackson.databind.ObjectMapper;

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

        if (resourceAsStream == null) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        return objectMapper.readValue(resourceAsStream, type);
    }
}