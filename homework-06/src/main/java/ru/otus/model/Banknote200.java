package ru.otus.model;

public class Banknote200 implements Banknote {

    private Nominal nominal = Nominal.TWO_HANDRED;
    private String serial;

    public Banknote200(String serial) {
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
