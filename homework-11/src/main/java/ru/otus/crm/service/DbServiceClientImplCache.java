package ru.otus.crm.service;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.HwCache;
import ru.otus.core.repository.DataTemplate;
import ru.otus.core.sessionmanager.TransactionManager;
import ru.otus.crm.model.Client;
import ru.otus.cachehw.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DbServiceClientImplCache implements DBServiceClient {
    private static final Logger log = LoggerFactory.getLogger(DbServiceClientImplCache.class);

    private final DataTemplate<Client> clientDataTemplate;
    private final TransactionManager transactionManager;
    private HwCache<String, Client> cache;

    public DbServiceClientImplCache(TransactionManager transactionManager, DataTemplate<Client> clientDataTemplate) {
        this.transactionManager = transactionManager;
        this.clientDataTemplate = clientDataTemplate;
        cache = new MyCache<String, Client>();
    }

    @Override
    public Client saveClient(Client client) {
        var clientPersist = transactionManager.doInTransaction(session -> {
            var clientCloned = client.clone();
            if (client.getId() == null) {
                clientDataTemplate.insert(session, clientCloned);
                log.info("created client: {}", clientCloned);
            }
            clientDataTemplate.update(session, clientCloned);
            log.info("updated client: {}", clientCloned);
            return clientCloned;
        });
        cache.put(clientPersist.getId().toString(), clientPersist);
        return clientPersist;
    }

    @Override
    public Optional<Client> getClient(long id) {
        return transactionManager.doInReadOnlyTransaction(session -> {
            var client = cache.get(String.valueOf(id));
            if (Objects.nonNull(client)) {
                log.info("cache client: {}", client);
                return Optional.of(client);
            }
            var clientOptional = clientDataTemplate.findById(session, id);
            log.info("client: {}", clientOptional);
            if (clientOptional.isPresent()) {
                client = clientOptional.get();
                cache.put(client.getId().toString(), client);
                return Optional.of(Client.unProxy(client));
            }
            return Optional.empty();
        });
    }

    @Override
    public List<Client> findAll() {
        return transactionManager.doInReadOnlyTransaction(session -> {
            var clientList = clientDataTemplate.findAll(session);
            log.info("clientList:{}", clientList);
            return clientList.stream().map(Client::unProxy).toList();
       });
    }
}
