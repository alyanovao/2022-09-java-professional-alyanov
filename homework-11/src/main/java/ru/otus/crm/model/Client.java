package ru.otus.crm.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "client")
    private List<Phone> phones;

    public Client(String name) {
        this.id = null;
        this.name = name;
        this.address = new Address();
        this.phones = new ArrayList<>();
    }

    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
        this.address = new Address();
        this.phones = new ArrayList<>();
    }

    public Client(Long id, String name, Address address, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phones = phones;
        initialize(phones);
    }

    public static Client unProxy(Client client) {
        return new Client(client.id, client.getName(),
                client.getAddress() instanceof HibernateProxy ? Hibernate.unproxy(client.getAddress(), Address.class) : client.getAddress(),
                Collections.unmodifiableList(client.getPhones()));
    }

    private void initialize(List<Phone> phones) {
        phones.forEach(phone -> phone.setClient(this));
    }

    public Client() {
    }

    @Override
    public Client clone() {
        return new Client(this.id, this.name, new Address(address.getId(), address.getStreet()), new ArrayList<>(phones));
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Addres{" +
                "id='" + address.getId() + '\'' +
                "street='" + address.getStreet() + '\'' +
                "}, Phones[" + phones.stream()
                .map(phone -> phone.getId() + phone.getNumber())
                .collect(Collectors.joining(";")) +
                "]" +
                '}';
    }
}
