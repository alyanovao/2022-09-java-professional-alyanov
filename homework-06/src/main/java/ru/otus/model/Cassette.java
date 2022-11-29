package ru.otus.model;

import ru.otus.exception.NoEqualsNominalException;
import ru.otus.exception.TooManyBanknotesException;
import ru.otus.utility.Static;

import java.util.*;

public class Cassette {

    private final Deque<Banknote> banknotes;
    private final Nominal nominal;

    public Cassette(Nominal nominal) {
        this.nominal = nominal;
        this.banknotes = new LinkedList<>();
    }

    public Integer getCassetteSize() {
        return Static.CASSETTE_SIZE;
    }

    public Nominal getNominal() {
        return nominal;
    }

    public Integer countBanknote() {
        return banknotes.size();
    }

    private boolean checkNominal(Nominal nominal) {
        return !this.getNominal().equals(nominal);
    }

    private boolean checkMaxCapacityCassette() {
        return (banknotes.size() > getCassetteSize());
    }

    public void addBanknote(Banknote banknote) {
        if (checkNominal(banknote.getNominal())) {
            throw new NoEqualsNominalException("not valid nominal");
        }
        if (checkMaxCapacityCassette()) {
            throw new TooManyBanknotesException("cassette is full");
        }
        banknotes.add(banknote);
    }

    public List<Banknote> extractBanknote(long count) {
        List<Banknote> outBanknotes = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            outBanknotes.add(banknotes.poll());
        }
        return outBanknotes;
    }
}
