package ru.otus.util;

import ru.otus.exception.ClassNotFoundException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ReflectionUtility {
    private ReflectionUtility() {
    }

    public static boolean hasAnnotation(Method method, Class<? extends Annotation> annotationClass) {
        return method.isAnnotationPresent(annotationClass);
    }

    public static Class<?> getClassByInstance(Object classInstance) {
        return getClassByName(classInstance.getClass().getInterfaces()[0].getName());
    }

    public static Class<?> getClassByName(String className) {
        ClassLoader classLoader = new MyClassLoader();
        try {
            return classLoader.loadClass(className);
        } catch (Exception e) {
            throw new ClassNotFoundException(e);
        }
    }
}
