package ru.otus.service;

import ru.otus.model.*;

import java.util.List;
import java.util.UUID;

public class EncashmentImpl implements Encashment {

    private static final int SIZE_CASSETTE = 50;

    private List<Cassette> cassettes;

    public EncashmentImpl(List<Cassette> cassettes) {
        this.cassettes = cassettes;
    }

    @Override
    public List<Cassette> inputMoney() {
        Nominal[] nominals = Nominal.values();
        for (int i = 0; i < nominals.length; i++) {
            var cassette = new Cassette();
            for (int y = 0; y < SIZE_CASSETTE; y++) {
                cassette.addBanknote(new Banknote(nominals[i], UUID.randomUUID().toString()));
            }
            cassettes.add(cassette);
        }
        return cassettes;
    }
}
