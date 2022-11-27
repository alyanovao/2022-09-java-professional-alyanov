package ru.otus.service;

import ru.otus.exception.ApplicationException;
import ru.otus.model.Banknote;
import ru.otus.model.Cassette;
import ru.otus.model.Nominal;
import ru.otus.processor.OutputService;
import ru.otus.processor.OutputServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculationModuleImpl implements CalculationModule {

    private CassetteControlModule module;
    HashMap<Nominal, Integer> cache;
    OutputService outputService = new OutputServiceImpl();

    public CalculationModuleImpl(CassetteControlModule module) {
        cache = new HashMap<>();
        this.module = module;
        var cassettes = this.module.getCassettes();
        for(Cassette cassette: cassettes) {
            cache.put(cassette.getNominal(), cassette.countBanknote());
        }
    }

    @Override
    public boolean isEnoughBanknoteInCassette(int count, Nominal nominal) {
        return cache.get(nominal) > count;
    }

    @Override
    public List<Banknote> getCash(long summ) {
        List<Banknote> cash = new ArrayList<>();
        HashMap<Nominal, Integer> temp = new HashMap<>();

        long sum = summ;
        int div = 0;
        int mod = 0;
        Nominal[] nominal = Nominal.values();
        for(int i = nominal.length - 1; i >= 0; i--) {
            if (sum == 0) {
                break;
            }
            div = (int) (sum / nominal[i].getValue());
            mod = (int) (sum % nominal[i].getValue());
            outputService.outputMessage("div = " + div);
            outputService.outputMessage("mod = " + mod);
            if (div > 0 && isEnoughBanknoteInCassette(div, nominal[i])) {
                temp.put(nominal[i], div);
                sum = mod;
            }
        }
        if (sum > 0) {
            throw new ApplicationException("Amount can not be given");
        }
        for(Map.Entry<Nominal, Integer> entry: temp.entrySet()) {
            cash.addAll(module.extractBanknote(entry.getValue(), entry.getKey()));
            countCache(entry.getKey());
        }
        return cash;
    }

    private void countCache(Nominal key) {
        cache.put(key, module.getCassetteByNominal(key).countBanknote());
    }

    @Override
    public long getSum() {
        long sum = 0;
        for (Map.Entry<Nominal, Integer> nominal : cache.entrySet()) {
            sum = sum + nominal.getKey().getValue() * nominal.getValue();
        }
        return sum;
    }
}
