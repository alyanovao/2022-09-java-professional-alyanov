package ru.otus.model;

import java.util.List;

public class ATM {

    public ATM() {
    }

    private List<Cassette> cassettes;

    public ATM(List<Cassette> cassettes) {
        this.cassettes = cassettes;
    }

    public List<Cassette> getCassettes() {
        return cassettes;
    }
}
