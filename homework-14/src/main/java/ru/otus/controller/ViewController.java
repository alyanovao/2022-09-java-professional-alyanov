package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.dto.ClientDto;
import ru.otus.model.Address;
import ru.otus.model.Client;
import ru.otus.model.Phone;
import ru.otus.services.ClientService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final ClientService clientService;

    @GetMapping("/")
    public String getClients(Model model) {
        val clients = clientService.getAllClients();
        List<ClientDto> clientsDto = clients.stream().map(client -> ClientDto.toDto(client)).collect(Collectors.toList());
        model.addAttribute("clients", clientsDto);
        return "clients";
    }

    @GetMapping(value = {"/clientAdd"})
    public String authorEditPage(Model model) {
        model.addAttribute("clientDto", new ClientDto());
        return "clientAdd";
    }

    @PostMapping("/clientAdd")
    public String addClient(ClientDto clientDto) {
        Set<Address> addresses = Arrays.stream(clientDto.getStreet().split(";")).map(address -> new Address(null, address, null)).collect(Collectors.toSet());
        Set<Phone> phones = Arrays.stream(clientDto.getPhone().split(";")).map(phone -> new Phone(null, phone, null)).collect(Collectors.toSet());
        Client client = new Client(clientDto.getName(), addresses, phones);
        clientService.saveClient(client);
        return "redirect:/";
    }
}
