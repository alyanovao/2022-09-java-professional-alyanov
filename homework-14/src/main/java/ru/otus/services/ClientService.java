package ru.otus.services;

import ru.otus.model.Client;

import java.util.List;

public interface ClientService {
    Client getClientById(Long id);
    List<Client> getAllClients();
    Client saveClient(Client client);
}
