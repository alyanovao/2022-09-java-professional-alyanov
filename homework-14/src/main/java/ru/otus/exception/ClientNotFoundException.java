package ru.otus.exception;

public class ClientNotFoundException extends ApplicationException {

    public ClientNotFoundException(String message) {
        super(message);
    }

}