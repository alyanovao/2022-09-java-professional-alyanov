package ru.otus.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Cassette {

    private static final int size = 50;
    private Deque<Banknote> banknotes;

    public Cassette() {
        this.banknotes = new LinkedList<>();
    }

    public Integer getCassetteSize() {
        return size;
    }

    public Nominal getNominal() {
        return banknotes.getFirst().getNominal();
    }

    public Integer countBanknote() {
        return banknotes.size();
    }

    public void addBanknote(Banknote banknote) {
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
