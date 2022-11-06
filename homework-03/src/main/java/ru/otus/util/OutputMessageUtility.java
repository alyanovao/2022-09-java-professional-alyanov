package ru.otus.util;

import ru.otus.exception.ApplicationException;

public class OutputMessageUtility {
    private OutputMessageUtility() {
        throw new ApplicationException("This is Utility class");
    }

    public static void outputString(String message) {
        System.out.println(message);
    }
}
