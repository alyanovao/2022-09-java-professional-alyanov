package ru.otus.service;

import ru.otus.annotations.Log;
import ru.otus.util.OutputMessageUtility;

public class TestLoggingServiceImpl implements TestLoggingService {

    public static final String CALCULATE = "calculate: ";

    @Log
    @Override
    public void calculation(int param1) {
        OutputMessageUtility.outputString(CALCULATE + String.valueOf(param1));
    }

    @Override
    public void calculation(int param1, int param2) {
        OutputMessageUtility.outputString(CALCULATE + String.valueOf(param1 + param2));
    }

    @Log
    @Override
    public void calculation(int param1, int param2, int param3) {
        OutputMessageUtility.outputString(CALCULATE + String.valueOf(param1 + param2 + param3));
    }
}
