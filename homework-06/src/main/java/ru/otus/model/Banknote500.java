package ru.otus.model;

public class Banknote500 implements Banknote {
    private Nominal nominal = Nominal.FIVE_HANDRED;
    private String serial;

    public Banknote500(String serial) {
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
