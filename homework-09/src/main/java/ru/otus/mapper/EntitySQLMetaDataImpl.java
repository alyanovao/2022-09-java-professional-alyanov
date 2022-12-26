package ru.otus.mapper;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData {

    private final EntityClassMetaData<?> entityClassMetaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData<?> entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public String getSelectAllSql() {
        return String.format("SELECT %S FROM %S", getColumns(entityClassMetaData), entityClassMetaData.getName());
    }

    @Override
    public String getSelectByIdSql() {
        return String.format("SELECT %S FROM %S WHERE %S = ?", getColumns(entityClassMetaData),
                entityClassMetaData.getName(), entityClassMetaData.getIdField().getName());
    }

    @Override
    public String getInsertSql() {
        return String.format("INSERT INTO %s VALUES (%s)", getInsertParam(entityClassMetaData),
                entityClassMetaData.getFieldsWithoutId().stream()
                        .map(field -> "?")
                        .collect(Collectors.joining(", ")));
    }

    @Override
    public String getUpdateSql() {
        return String.format("UPDATE %S SET NAME = VALUE WHERE Id = ?", getUpdateParam(entityClassMetaData));
    }

    private String getColumns(EntityClassMetaData<?> entityClassMetaData) {
        return entityClassMetaData.getAllFields().stream().map(Field::getName).collect(Collectors.joining(", "));
    }

    private String getInsertParam(EntityClassMetaData<?> entityClassMetaData) {
        return entityClassMetaData.getName() + "(" +
                entityClassMetaData.getFieldsWithoutId().stream()
                        .map(Field::getName).collect(Collectors.joining(", ")) + ")";
    }

    private String getUpdateParam(EntityClassMetaData<?> entityClassMetaData) {
        return entityClassMetaData.getName() + entityClassMetaData.getFieldsWithoutId()
                .stream()
                .map(field -> "SET " + field.getName() + " = ?").collect(Collectors.joining(", "));
    }
}
