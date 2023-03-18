package ru.otus.protobuf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.protobuf.service.Runner;

@SpringBootApplication
public class ClientWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientWebApplication.class, args);
        Runner.run();
    }
}