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
        var cassette50 = new Cassette();
        var cassette100 = new Cassette();
        var cassette200 = new Cassette();
        var cassette500 = new Cassette();
        var cassette1000 = new Cassette();
        var cassette2000 = new Cassette();
        var cassette5000 = new Cassette();

        for(int i = 0; i < SIZE_CASSETTE; i++) {
            cassette50.addBanknote(new Banknote50(UUID.randomUUID().toString()));
            cassette100.addBanknote(new Banknote100(UUID.randomUUID().toString()));
            cassette200.addBanknote(new Banknote200(UUID.randomUUID().toString()));
            cassette500.addBanknote(new Banknote500(UUID.randomUUID().toString()));
            cassette1000.addBanknote(new Banknote1000(UUID.randomUUID().toString()));
            cassette2000.addBanknote(new Banknote2000(UUID.randomUUID().toString()));
            cassette5000.addBanknote(new Banknote5000(UUID.randomUUID().toString()));
        }
        cassettes.add(cassette50);
        cassettes.add(cassette100);
        cassettes.add(cassette200);
        cassettes.add(cassette500);
        cassettes.add(cassette1000);
        cassettes.add(cassette2000);
        cassettes.add(cassette5000);
        return cassettes;
    }
}
