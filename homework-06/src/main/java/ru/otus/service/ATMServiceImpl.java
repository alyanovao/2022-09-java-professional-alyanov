package ru.otus.service;

import ru.otus.model.ATM;
import ru.otus.model.Banknote;
import ru.otus.model.Cassette;
import ru.otus.processor.OutputService;
import ru.otus.processor.OutputServiceImpl;

import java.util.List;

public class ATMServiceImpl implements ATMService {


    private CalculationModule calculation;
    private CassetteControlModule control;
    private OutputService outputService;

    public ATMServiceImpl(List<Cassette> cassettes) {
        this.control = new CassetteControlModuleImpl(cassettes);
        this.calculation = new CalculationModuleImpl(control);
        outputService = new OutputServiceImpl();
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
