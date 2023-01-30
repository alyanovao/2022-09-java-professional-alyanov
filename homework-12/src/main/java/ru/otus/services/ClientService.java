package ru.otus.services;

import ru.otus.crm.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Optional<Client> getById(long id);
    List<Client> getClients();
    Client saveClient(Client client);
}
