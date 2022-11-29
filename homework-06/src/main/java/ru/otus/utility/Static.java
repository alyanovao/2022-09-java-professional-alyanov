package ru.otus.utility;

import ru.otus.exception.ApplicationException;

public class Static {

    private Static() {
        throw new ApplicationException("Utility class");
    }

    public static final int CASSETTE_SIZE = 50;
}
