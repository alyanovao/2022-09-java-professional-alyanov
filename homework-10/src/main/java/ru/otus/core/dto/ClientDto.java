package ru.otus.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.otus.crm.model.Address;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.Phone;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long id;
    private String name;
    private Address address;
    private List<Phone> phones;

    public static ClientDto toDto(Client client) {
        return new ClientDto(client.getId(), client.getName(),
                new Address(client.getAddress().getId(), client.getAddress().getStreet()),
                client.getPhones().stream().map(phone -> new Phone(phone.getId(), phone.getNumber()))
                .toList());
    }


}

