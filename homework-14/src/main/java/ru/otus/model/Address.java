package ru.otus.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@ToString
@RequiredArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    private final Long Id;

    @NonNull
    private final String street;

    private final Long clientId;
}
