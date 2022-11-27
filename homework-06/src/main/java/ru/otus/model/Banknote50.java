package ru.otus.model;

public class Banknote50 implements Banknote {

    private Nominal nominal = Nominal.FIFTY;
    private String serial;

    public Banknote50(String serial) {
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
