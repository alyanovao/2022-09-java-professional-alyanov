package ru.otus.model;

public class Banknote {

    private final Nominal nominal;
    private final String serial;

    public Banknote(Nominal nominal, String serial) {
        this.nominal = nominal;
        this.serial = serial;
    }

    public Nominal getNominal() {
        return nominal;
    }

    public String getSerial() {
        return serial;
    }

    @Override
    public String toString() {
        return "Banknote{" +
                "nominal=" + nominal +
                ", serial='" + serial + '\'' +
                '}' + "\n";
    }
}
