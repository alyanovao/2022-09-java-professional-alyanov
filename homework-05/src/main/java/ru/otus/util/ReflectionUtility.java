package ru.otus.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ReflectionUtility {
    private ReflectionUtility() {
    }

    public static boolean hasAnnotation(Method method, Class<? extends Annotation> annotationClass) {
        return method.isAnnotationPresent(annotationClass);
    }

    public static Class<?> getClassByName(String className) throws ClassNotFoundException {
        ClassLoader classLoader = new MyClassLoader();
        return classLoader.loadClass(className);
    }

    public static Method getMethodImplementationByInterface(String methodName, Method methodInterface) throws NoSuchMethodException, ClassNotFoundException {
        Class<?> clazz = getClassByName(methodName);
        return clazz.getMethod(methodInterface.getName(), methodInterface.getParameterTypes());
    }
}
