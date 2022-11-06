package ru.otus.dto;

import java.lang.reflect.Method;
import java.util.List;

public class TestAnnotationDto {
    Method beforeAnnotation;
    List<Method> testAnnotations;
    Method afterAnnotation;

    public TestAnnotationDto(Method beforeAnnotation, List<Method> testAnnotations, Method afterAnnotation) {
        this.beforeAnnotation = beforeAnnotation;
        this.testAnnotations = testAnnotations;
        this.afterAnnotation = afterAnnotation;
    }

    public Method getBeforeAnnotation() {
        return beforeAnnotation;
    }

    public List<Method> getTestAnnotations() {
        return testAnnotations;
    }

    public Method getAfterAnnotation() {
        return afterAnnotation;
    }
}
