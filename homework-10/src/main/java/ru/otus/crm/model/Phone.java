package ru.otus.crm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Phone {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "number")
    private String number;

    @ManyToOne
    private Client client;

    public Phone(Long id, String number) {
        Id = id;
        this.number = number;
    }
}
