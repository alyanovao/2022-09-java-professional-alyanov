package ru.otus.service;

import ru.otus.exception.NoCassetteFountException;
import ru.otus.model.Banknote;
import ru.otus.model.Cassette;
import ru.otus.model.Nominal;

import java.util.Collections;
import java.util.List;

public class CassetteControlModuleImpl implements CassetteControlModule {

    private List<Cassette> cassettes;

    public CassetteControlModuleImpl(List<Cassette> cassettes) {
        this.cassettes = cassettes;
    }

    public List<Cassette> getCassettes() {
        return Collections.unmodifiableList(cassettes);
    }

    @Override
    public void addCassette(Cassette cassette) {
        cassettes.add(cassette);
    }

    @Override
    public void extractCassette(Cassette cassette) {
        cassettes.remove(cassette);
    }

    @Override
    public boolean addBanknote(Banknote banknote) {
        Cassette cassette = getCassetteByNominal(banknote.getNominal());
        cassette.addBanknote(banknote);
        return true;
    }

    @Override
    public List<Banknote> extractBanknote(long count, Nominal nominal) {
        Cassette cas = getCassetteByNominal(nominal);
        return cas.extractBanknote(count);
    }

    @Override
    public Cassette getCassetteByNominal(Nominal nominal) {
        for(Cassette cas: cassettes) {
            if (cas.getNominal().equals(nominal)) {
                return cas;
            }
        }
        throw new NoCassetteFountException("No cassette found by this nominal");
    }
}
