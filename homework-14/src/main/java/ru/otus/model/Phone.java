package ru.otus.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@RequiredArgsConstructor
public class Phone {

    @Id
    private final Long Id;

    @NonNull
    private final String number;

    @NonNull
    private final Long clientId;

}