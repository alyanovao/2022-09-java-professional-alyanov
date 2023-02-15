package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dto.ClientDto;
import ru.otus.services.ClientService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/clients1")
    public ResponseEntity<List<ClientDto>> getClients() {
        val clients = clientService.getAllClients();
        List<ClientDto> clientsDto = new ArrayList<>();
        clients.forEach(client -> clientsDto.add(ClientDto.toDto(client)));
        return new ResponseEntity<>(clientsDto, HttpStatus.OK);
    }
}
