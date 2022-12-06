package ru.otus.service;

import ru.otus.model.Banknote;
import ru.otus.model.Cassette;
import ru.otus.model.Nominal;

import java.util.List;

public interface CassetteControlModule {

    List<Cassette> getCassettes();

    Cassette getCassetteByNominal(Nominal nominal);
    void addCassette(Cassette cassette);
    void extractCassette(Cassette cassette);
    boolean addBanknote(Banknote banknote);
    List<Banknote> extractBanknote(long count, Nominal nominal);
}
