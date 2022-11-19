package ru.otus;

import ru.otus.proxy.Ioc;
import ru.otus.service.TestLoggingCircuitService;
import ru.otus.service.TestLoggingService;
import ru.otus.service.TestLoggingServiceCalculate;

public class Application {
    public static void main(String[] args) {
        TestLoggingService service = (TestLoggingService) Ioc.createTestLoggingServiceClass(new TestLoggingServiceCalculate());
        service.calculation(1);
        service.calculation(1, 2);
        service.calculation(1, 2, 3);

        TestLoggingService serviceSecondary = (TestLoggingService) Ioc.createTestLoggingServiceClass(new TestLoggingCircuitService());
        serviceSecondary.calculation(1);
        serviceSecondary.calculation(1, 2);
        serviceSecondary.calculation(1, 2, 3);
    }
}
