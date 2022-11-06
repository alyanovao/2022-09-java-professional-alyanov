package ru.otus.service;

import ru.otus.util.OutputMessageUtility;
import ru.otus.util.ReflectionHelper;

import java.lang.reflect.Method;

public class ReflectionService {

    public static Class<?> getClassInstance(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

    public static Object getNewClassInstance(Class<?> clazz, Object arg) {
        if (arg == null) {
            return ReflectionHelper.instantiate(clazz);
        }
        else {
            return ReflectionHelper.instantiate(clazz, arg);
        }
    }

    public static boolean hasAnnotation(Method method, Class annotationClass) {
        return method.isAnnotationPresent(annotationClass);
    }

    public static boolean callMethod(Object object, String methodName) {
        try {
            ReflectionHelper.callMethod(object, methodName);
        }
        catch (Exception e) {
            OutputMessageUtility.outputString("Exception :: " + e.getMessage());
            return false;
        }
        return true;
    }
}
