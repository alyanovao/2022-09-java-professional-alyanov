package ru.otus.appcontainer.exception;

public class BeanLoadException extends ApplicationException {
    public BeanLoadException(String message) {
        super(message);
    }

    public BeanLoadException(Throwable throwable) {
        super(throwable);

    }
}
