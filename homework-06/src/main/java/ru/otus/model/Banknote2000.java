package ru.otus.model;

public class Banknote2000 implements Banknote {
    private Nominal nominal = Nominal.TWO_THOUSAND;
    private String serial;

    public Banknote2000(String serial) {
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
