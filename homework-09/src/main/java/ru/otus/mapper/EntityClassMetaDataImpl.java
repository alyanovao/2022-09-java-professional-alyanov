package ru.otus.mapper;

import ru.otus.crm.model.Id;
import ru.otus.exception.NoFoundMethodException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    private final String name;
    private final Field field;
    private final List<Field> fields;
    private final List<Field> simpleFiels;
    private final Constructor<T> constructor;

    public EntityClassMetaDataImpl(Class<T> clazz) {
        this.name = clazz.getSimpleName();

        this.fields = Arrays.stream(clazz.getDeclaredFields()).toList();
        this.field = fields.stream()
                .filter(field1 -> field1.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow();

        this.simpleFiels = fields.stream()
                .filter(field1 -> !(field1.isAnnotationPresent(Id.class)))
                .toList();
        try {
            this.constructor = clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new NoFoundMethodException(e);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Constructor getConstructor() {
        return constructor;
    }

    @Override
    public Field getIdField() {
        return field;
    }

    @Override
    public List<Field> getAllFields() {
        return fields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return simpleFiels;
    }
}
