package ru.otus.exception;

public class ApplicationException extends RuntimeException {

    public ApplicationException() {
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ApplicationException(String message) {
        super(message);
    }
}
