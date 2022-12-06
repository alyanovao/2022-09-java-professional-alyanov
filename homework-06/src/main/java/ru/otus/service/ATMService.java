package ru.otus.service;

import ru.otus.model.Banknote;

import java.util.List;

public interface ATMService {
    List<Banknote> getCash(long sum);
    long getSum();
}
