package ru.otus.dataprocessor;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import ru.otus.model.Measurement;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResourcesFileLoader implements Loader {
    private final String res;

    public ResourcesFileLoader(String fileName) {
        try (
            var in = this.getClass().getClassLoader().getResourceAsStream(fileName);
            var buffer = new BufferedInputStream(in);
            var reader = new InputStreamReader(buffer);
            var bufferReader = new BufferedReader(reader)
        ) {
            res = bufferReader.readLine();

        } catch (IOException e) {
            throw new FileProcessException(e);
        }
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Measurement>>(){}.getType();
        List<Measurement> list = gson.fromJson(res, type);
        return Collections.unmodifiableList(list);
    }
}
