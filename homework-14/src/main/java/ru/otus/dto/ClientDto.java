package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.model.Address;
import ru.otus.model.Client;
import ru.otus.model.Phone;

import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long id;
    private String name;
    private String street;
    private String phone;

    public static ClientDto toDto(Client client) {
        return new ClientDto(client.getId(), client.getName(),
                client.getAddress().stream().map(Address::getStreet).collect(Collectors.joining(", ")),
                client.getPhones().stream().map(Phone::getNumber).collect(Collectors.joining(", ")));
    }
}
