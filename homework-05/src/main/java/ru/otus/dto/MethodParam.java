package ru.otus.dto;

public class MethodParam {
    private boolean hasAnnotation;
    private long countParam;

    public MethodParam(boolean hasAnnotation, long countParam) {
        this.hasAnnotation = hasAnnotation;
        this.countParam = countParam;
    }

    public boolean isHasAnnotation() {
        return hasAnnotation;
    }

    public long getCountParam() {
        return countParam;
    }
}
