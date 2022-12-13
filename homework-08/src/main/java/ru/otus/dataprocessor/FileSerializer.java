package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {
    private final String path;
    private final ObjectMapper objectMapper;

    public FileSerializer(String fileName) {
        this.path = fileName;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void serialize(Map<String, Double> data) {
        //формирует результирующий json и сохраняет его в файл
        var file = new File(path);
        try {
            objectMapper.writeValue(file, data);
        } catch (IOException e) {
            throw new FileProcessException(e);
        }
    }
}
