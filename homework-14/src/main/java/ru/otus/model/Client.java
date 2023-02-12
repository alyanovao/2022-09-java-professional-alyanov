package ru.otus.model;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Getter
@Setter
@ToString
@Table(name = "client")
public class Client implements Cloneable {

    @Id
    private final Long id;

    @NonNull
    private final String name;

    //@NonNull
    @MappedCollection(idColumn = "client_id")
    private final Set<Address> address;

    @MappedCollection(idColumn = "client_id")
    private final Set<Phone> phones;

    public Client(@NonNull String name, @NonNull Set<Address> address, Set<Phone> phones) {
        this(null, name, address, phones);
    }

    @PersistenceCreator
    public Client(Long id, @NonNull String name, @NonNull Set<Address> address, Set<Phone> phones) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phones = phones;
    }
}