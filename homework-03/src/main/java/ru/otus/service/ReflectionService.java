package ru.otus.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public interface ReflectionService {
    Class<?> getClassInstance(String className) throws ClassNotFoundException;
    Object getNewClassInstance(Class<?> clazz, Object arg);
    boolean hasAnnotation(Method method, Class<? extends Annotation> annotation);
    boolean callMethod(Object object, String methodName);
}
