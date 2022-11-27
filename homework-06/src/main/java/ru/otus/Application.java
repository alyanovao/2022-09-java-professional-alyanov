package ru.otus;

import ru.otus.model.Cassette;
import ru.otus.processor.OutputService;
import ru.otus.processor.OutputServiceImpl;
import ru.otus.service.*;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<Cassette> cassettes = new ArrayList<>();
        Encashment encashment = new EncashmentImpl(cassettes);
        encashment.inputMoney();

        CassetteControlModule module = new CassetteControlModuleImpl(cassettes);
        CalculationModule calculation = new CalculationModuleImpl(module);
        OutputService output = new OutputServiceImpl();
        output.outputMessage(calculation.getCash(2400).toString());
        output.outputMessage(String.valueOf(calculation.getSum()));
    }
}
