package ru.otus.model;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

public enum Nominal {
    FIFTY(50),
    HANDRED(100),
    TWO_HANDRED(200),
    FIVE_HANDRED(500),
    THOUSAND(1000),
    TWO_THOUSAND(2000),
    FIVE_THOUSAND(5000);

    private int banknoteNominal;

    private static final Map<Integer, Nominal> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(Nominal.values()),
            Nominal::getValue
    );

    Nominal(int banknoteNominal) {
        this.banknoteNominal = banknoteNominal;
    }

    public int getValue() {
        return banknoteNominal;
    }

    public static Nominal from(int banknoteNominal) {
        return LOOKUP.get(banknoteNominal);
    }
}
