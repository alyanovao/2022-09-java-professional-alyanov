package ru.otus;

import ru.otus.proxy.Ioc;
import ru.otus.service.TestLoggingService;

public class Application {
    public static void main(String[] args) {
        TestLoggingService service = Ioc.createTestLoggingServiceClass();
        service.calculation(1);
        service.calculation(1, 2);
        service.calculation(1, 2, 3);
    }
}
