package ru.otus.processor;

import ru.otus.mapper.EntityClassMetaData;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

public class SqlUtils {
    public static String getColumns(EntityClassMetaData<?> entityClassMetaData) {
        return entityClassMetaData.getAllFields().stream().map(Field::getName).collect(Collectors.joining(", "));
    }
    public static String getInsertParam(EntityClassMetaData<?> entityClassMetaData) {
        return entityClassMetaData.getName() + "(" +
                entityClassMetaData.getFieldsWithoutId().stream()
                        .map(Field::getName).collect(Collectors.joining(", ")) + ")";
    }

    public static String getUpdateParam(EntityClassMetaData<?> entityClassMetaData) {
        return entityClassMetaData.getName() + entityClassMetaData.getFieldsWithoutId()
                .stream()
                .map(field -> "SET " + field.getName() + " = ?").collect(Collectors.joining(", "));
    }
}
