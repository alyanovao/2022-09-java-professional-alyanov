package ru.otus.model;

public class Banknote5000 implements Banknote {

    private Nominal nominal = Nominal.FIVE_THOUSAND;
    private String serial;

    public Banknote5000(String serial) {
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
