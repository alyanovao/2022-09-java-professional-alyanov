package ru.otus.service;

import ru.otus.util.OutputMessageUtility;
import ru.otus.util.ReflectionHelper;

import java.lang.reflect.Method;

public class ReflectionServiceImpl implements ReflectionService {

    @Override
    public Class<?> getClassInstance(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

    @Override
    public Object getNewClassInstance(Class<?> clazz, Object arg) {
        if (arg == null) {
            return ReflectionHelper.instantiate(clazz);
        }
        else {
            return ReflectionHelper.instantiate(clazz, arg);
        }
    }

    @Override
    public boolean hasAnnotation(Method method, Class annotationClass) {
        return method.isAnnotationPresent(annotationClass);
    }

    @Override
    public boolean callMethod(Object object, String methodName) {
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
