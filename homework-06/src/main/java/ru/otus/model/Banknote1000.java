package ru.otus.model;

public class Banknote1000 implements Banknote {
    private Nominal nominal = Nominal.THOUSAND;
    private String serial;

    public Banknote1000(String serial) {
        this.serial = serial;
    }

    @Override
    public Nominal getNominal() {
        return nominal;
    }

    @Override
    public String getSerial() {
        return serial;
    }
}
