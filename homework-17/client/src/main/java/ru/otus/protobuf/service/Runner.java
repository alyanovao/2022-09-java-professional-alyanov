package ru.otus.protobuf.service;

import org.springframework.stereotype.Component;

@Component
public class Runner {

    private static ClientService service;

    public Runner(ClientService service) {
        this.service = service;
    }

    public static void run() {
        service.createMessage();
    }
}
