package ru.otus.mapper;

import ru.otus.processor.SqlUtils;

import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData {

    private final EntityClassMetaData<?> entityClassMetaData;

    public EntitySQLMetaDataImpl(EntityClassMetaData<?> entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public String getSelectAllSql() {
        return String.format("SELECT %S FROM %S", SqlUtils.getColumns(entityClassMetaData), entityClassMetaData.getName());
    }

    @Override
    public String getSelectByIdSql() {
        return String.format("SELECT %S FROM %S WHERE %S = ?", SqlUtils.getColumns(entityClassMetaData),
                entityClassMetaData.getName(), entityClassMetaData.getIdField().getName());
    }

    @Override
    public String getInsertSql() {
        return String.format("INSERT INTO %s VALUES (%s)", SqlUtils.getInsertParam(entityClassMetaData),
                entityClassMetaData.getFieldsWithoutId().stream()
                        .map(field -> "?")
                        .collect(Collectors.joining(", ")));
    }

    @Override
    public String getUpdateSql() {
        return String.format("UPDATE %S SET NAME = VALUE WHERE Id = ?", SqlUtils.getUpdateParam(entityClassMetaData));
    }
}
