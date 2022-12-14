package ru.otus.mapper;

import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.executor.DbExecutor;
import ru.otus.exception.ApplicationException;
import ru.otus.exception.NoFoundException;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private final EntityClassMetaData<T> entityClassMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor,
                            EntitySQLMetaData entitySQLMetaData,
                            EntityClassMetaData<T> entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id), resultSet -> {
            try {
                if (resultSet.next()) {
                    return createInstance(resultSet);
                }
                return null;
            } catch (SQLException e) {
                throw new ApplicationException(e);
            }
        });
    }

    @Override
    public List<T> findAll(Connection connection) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectAllSql(), Collections.emptyList(), resultSet -> {
            try {
                List<T> list = new ArrayList<>();
                if (resultSet.next()) {
                    var field = createInstance(resultSet);
                    list.add(field);
                }
                return list;
            } catch (SQLException e) {
                throw new NoFoundException(e);
            }
        }).orElseThrow();
    }

    @Override
    public long insert(Connection connection, T instance) {
        return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(),
                entityClassMetaData
                .getFieldsWithoutId()
                .stream()
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        return field.get(instance);
                    } catch (IllegalAccessException e) {
                        throw new ApplicationException(e);
                    }
                }).toList()
        );
    }

    @Override
    public void update(Connection connection, T instance) {
        dbExecutor.executeStatement(connection, entitySQLMetaData.getUpdateSql(),
                entityClassMetaData
                        .getFieldsWithoutId()
                        .stream()
                        .map(field -> {
                            field.setAccessible(true);
                            try {
                                return field.get(instance);
                            } catch (IllegalAccessException e) {
                                throw new ApplicationException(e);
                            }
                        })
                        .toList());
    }

    private T createInstance(ResultSet resultSet) {
        try {
            T instance = entityClassMetaData.getConstructor().newInstance();

            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                Field field = instance.getClass().getDeclaredField(resultSet.getMetaData().getColumnName(i));
                field.setAccessible(true);
                field.set(instance, resultSet.getObject(i));
            }
            return instance;
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }
}
