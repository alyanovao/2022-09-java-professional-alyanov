package ru.otus.model;

import java.util.List;
import java.util.Objects;

public class ObjectForMessage {
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        if (Objects.isNull(this.data)) {
            this.data = data;
        }
    }
}
