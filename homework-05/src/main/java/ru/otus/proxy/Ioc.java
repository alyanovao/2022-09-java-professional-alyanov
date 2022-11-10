package ru.otus.proxy;


import ru.otus.service.TestLoggingService;
import ru.otus.service.TestLoggingServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Ioc {
    private Ioc() {
    }

    public static TestLoggingService createTestLoggingServiceClass() {
        InvocationHandler handler = new MyInvocationHandler(new TestLoggingServiceImpl());
        return (TestLoggingService) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingService.class}, handler);

    }
}
