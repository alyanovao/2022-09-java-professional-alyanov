package ru.otus.exception;

public class TooManyInterfacesException extends ApplicationException {

    public TooManyInterfacesException() {
    }

    public TooManyInterfacesException(Throwable cause) {
        super(cause);
    }

    public TooManyInterfacesException(String message) {
        super(message);
    }
}
