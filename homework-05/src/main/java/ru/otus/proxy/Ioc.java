package ru.otus.proxy;


import ru.otus.util.ReflectionUtility;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Ioc {
    private Ioc() {
    }

    public static Object createTestLoggingServiceClass(Object classInstance) {
        InvocationHandler handler = new MyInvocationHandler(classInstance);
        Class<?> classInterface = ReflectionUtility.getClassByInstance(classInstance);
        return Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{classInterface}, handler);

    }
}
