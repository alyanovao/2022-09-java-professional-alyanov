package ru.otus.exception;

public class NoFoundMethodException extends ApplicationException {

    public NoFoundMethodException(String message) {
        super(message);
    }

    public NoFoundMethodException(Throwable cause) {
        super(cause);
    }
}
