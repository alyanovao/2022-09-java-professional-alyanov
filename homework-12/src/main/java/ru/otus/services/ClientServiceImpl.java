package ru.otus.services;

import ru.otus.crm.model.Client;
import ru.otus.crm.service.DBServiceClient;

import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {

    private final DBServiceClient repository;

    public ClientServiceImpl(DBServiceClient repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Client> getById(long id) {
        return repository.getClient(id);
    }

    @Override
    public List<Client> getClients() {
        return repository.findAll();
    }

    @Override
    public Client saveClient(Client client) {
        return repository.saveClient(client);
    }
}
