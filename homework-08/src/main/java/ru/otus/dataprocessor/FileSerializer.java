package ru.otus.dataprocessor;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {
    private final String path;

    public FileSerializer(String fileName) {
        path = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        //формирует результирующий json и сохраняет его в файл
        Gson gson = new Gson();
        var serializeData = gson.toJson(data);
        try (var bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.write(serializeData);
        } catch (IOException e) {
            throw new FileProcessException(e);
        }
    }
}
