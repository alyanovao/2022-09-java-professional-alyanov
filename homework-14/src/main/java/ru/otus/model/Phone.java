package ru.otus.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    @Id
    private Long Id;

    @NonNull
    private String number;

    @NonNull
    private Long clientId;

}