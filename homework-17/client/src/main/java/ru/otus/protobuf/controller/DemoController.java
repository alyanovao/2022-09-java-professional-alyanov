package ru.otus.protobuf.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.protobuf.service.ClientService;

@RestController
public class DemoController {

    private final ClientService clientService;

    public DemoController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    private String hello() {
        return "hello, world";
    }

    @GetMapping("/exchange")
    private String getUsers() {
        clientService.createMessage();
        return "ok";
    }
}
