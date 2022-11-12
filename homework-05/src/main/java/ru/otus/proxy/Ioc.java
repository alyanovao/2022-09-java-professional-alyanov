package ru.otus.proxy;


import ru.otus.service.TestLoggingService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Ioc {
    private Ioc() {
    }

    public static TestLoggingService createTestLoggingServiceClass(Object classInstance) {
        InvocationHandler handler = new MyInvocationHandler(classInstance);
        return (TestLoggingService) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingService.class}, handler);

    }
}
