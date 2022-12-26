package ru.otus.exception;

public class NoFoundException extends ApplicationException {

    public NoFoundException(String message) {
        super(message);
    }

    public NoFoundException(Throwable cause) {
        super(cause);
    }
}
