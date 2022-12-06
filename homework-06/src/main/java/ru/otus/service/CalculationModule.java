package ru.otus.service;

import ru.otus.model.Banknote;
import ru.otus.model.Nominal;

import java.util.List;

public interface CalculationModule {
    boolean isEnoughBanknoteInCassette(int count, Nominal nominal);
    List<Banknote> getCash(long summ);
    long getSum();
}
