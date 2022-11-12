package ru.otus.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ReflectionUtility {
    private ReflectionUtility() {
    }

    public static boolean hasAnnotation(Method method, Class<? extends Annotation> annotationClass) {
        return method.isAnnotationPresent(annotationClass);
    }
}
