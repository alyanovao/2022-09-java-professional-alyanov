package ru.otus.services;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.otus.exception.ClientNotFoundException;
import ru.otus.model.Client;
import ru.otus.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    @Override
    public Client getClientById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ClientNotFoundException("client not found"));
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        val result = repository.findAll();
        result.forEach(clients::add);
        return clients;
    }

    @Override
    public Client saveClient(Client client) {
        return repository.save(client);
    }
}
