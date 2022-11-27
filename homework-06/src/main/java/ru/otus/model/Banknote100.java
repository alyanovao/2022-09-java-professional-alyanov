package ru.otus.model;

public class Banknote100 implements Banknote {

    private Nominal nominal = Nominal.HANDRED;
    private String serial;

    public Banknote100(String serial) {
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
