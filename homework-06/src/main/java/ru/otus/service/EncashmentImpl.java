package ru.otus.service;

import ru.otus.model.*;
import ru.otus.utility.Static;

import java.util.List;
import java.util.UUID;

public class EncashmentImpl implements Encashment {

    private final List<Cassette> cassettes;

    public EncashmentImpl(List<Cassette> cassettes) {
        this.cassettes = cassettes;
    }

    @Override
    public List<Cassette> inputMoney() {
        Nominal[] nominals = Nominal.values();
        for (Nominal nominal : nominals) {
            var cassette = new Cassette(nominal);
            for (int y = 0; y < Static.CASSETTE_SIZE; y++) {
                cassette.addBanknote(new Banknote(nominal, UUID.randomUUID().toString()));
            }
            cassettes.add(cassette);
        }
        return cassettes;
    }
}
