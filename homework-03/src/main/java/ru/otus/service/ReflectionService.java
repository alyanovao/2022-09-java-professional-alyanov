package ru.otus.service;

import java.lang.reflect.Method;

public interface ReflectionService {
    public Class<?> getClassInstance(String className) throws ClassNotFoundException;
    public Object getNewClassInstance(Class<?> clazz, Object arg);
    public boolean hasBefore(Method method);
    public boolean hasTest(Method method);
    public boolean hasAfter(Method method);
    public boolean callMethod(Object object, String methodName);
}
