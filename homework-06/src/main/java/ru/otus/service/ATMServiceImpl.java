package ru.otus.service;

import ru.otus.model.Banknote;
import ru.otus.processor.OutputService;

import java.util.List;

public class ATMServiceImpl implements ATMService {


    private CalculationModule calculation;
    private OutputService outputService;

    public ATMServiceImpl(CalculationModule calculation,
                          OutputService outputService) {
        this.calculation = calculation;
        this.outputService = outputService;
    }

    @Override
    public List<Banknote> getCash(long sum) {
        var cash = calculation.getCash(sum);
        outputService.outputMessage(cash.toString());
        return cash;
    }

    @Override
    public long getSum() {
        var sum = calculation.getSum();
        outputService.outputMessage(String.valueOf(sum));
        return sum;
    }
}
