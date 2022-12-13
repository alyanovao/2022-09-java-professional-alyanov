package ru.otus.dataprocessor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.model.Measurement;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ResourcesFileLoader implements Loader {
    private final String res;
    private final ObjectMapper objectMapper;

    public ResourcesFileLoader(String fileName) {
        res = fileName;
        objectMapper = new ObjectMapper();
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        try (
                var in = this.getClass().getClassLoader().getResourceAsStream(res);
                var buffer = new BufferedInputStream(in);
                var reader = new InputStreamReader(buffer);
                var bufferReader = new BufferedReader(reader)
        ) {
            StringBuilder builder = new StringBuilder();
            var line = bufferReader.readLine();
            while(line != null) {
                builder.append(line);
                line = bufferReader.readLine();
            }
            List<LinkedHashMap> list = objectMapper.readValue(builder.toString(), new TypeReference<List>() {});
            List<Measurement> listMeasurement = list.stream()
                    .map(e -> new Measurement((String) e.get("name"), (Double) e.get("value")))
                    .collect(Collectors.toList());
            return listMeasurement;
        } catch (IOException e) {
            throw new FileProcessException(e);
        }
    }
}
