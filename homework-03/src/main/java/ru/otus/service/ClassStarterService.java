package ru.otus.service;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.dto.TestAnnotationDto;
import ru.otus.exception.ApplicationException;
import ru.otus.util.OutputMessageUtility;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

public class ClassStarterService {

    private ClassStarterService() {
        throw new ApplicationException("This is Utility class");
    }

    public static void run(String className) {
            try {
                Class<?> clazz = ReflectionService.getClassInstance(className);
                TestAnnotationDto dto = getTestAnnotations(clazz);
                Map<String, Boolean> testResult = startTest(clazz, dto);
                checkTestResult(testResult);
            }
            catch (Exception e) {
                OutputMessageUtility.outputString("Exception start test class :: " + e.toString());
            }
        }

    private static List<Method> getListMethodWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return Arrays.stream(clazz.getMethods())
                .filter(method -> ReflectionService.hasAnnotation(method, annotationClass))
                .peek(method -> OutputMessageUtility.outputString(method.getName() + " has annotation " + annotationClass.getSimpleName()))
                .toList();
    }

    private static Method getMethodWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        List<Method> methods = getListMethodWithAnnotation(clazz, annotationClass);
        if (methods.size() > 1) {
            throw new ApplicationException("Methods with annotation " + annotationClass.getSimpleName() + " more one");
        }
        return methods.isEmpty() ? null : methods.get(0);
    }

    private static TestAnnotationDto getTestAnnotations(Class<?> clazz) {
        //В классе только один не обязательный метод before и after, с другой реализацией не сталкивался
        Method beforeMethod = getMethodWithAnnotation(clazz, Before.class);
        List<Method> testMethods = getListMethodWithAnnotation(clazz, Test.class);
        Method afterMethod = getMethodWithAnnotation(clazz, After.class);

        List<Method> randomMethods = new ArrayList<>(testMethods);
        Collections.shuffle(randomMethods);
        return new TestAnnotationDto(beforeMethod, randomMethods, afterMethod);
    }

    private static Map<String, Boolean> startTest(Class<?> clazz, TestAnnotationDto dto) {
        Map<String, Boolean> result = new HashMap<>();

        dto.getTestAnnotations().forEach(testMethod -> {
            OutputMessageUtility.outputString("----------------------------------");
            Object object = ReflectionService.getNewClassInstance(clazz, null);

            boolean executeBefore = true;
            if (Objects.nonNull(dto.getBeforeAnnotation())) {
                executeBefore = ReflectionService.callMethod(object, dto.getBeforeAnnotation().getName());
            }

            if (executeBefore) {
                result.put(testMethod.getName(), ReflectionService.callMethod(object, testMethod.getName()));
            }

            if (Objects.nonNull(dto.getAfterAnnotation())) {
                ReflectionService.callMethod(object, dto.getAfterAnnotation().getName());
            }
        });
        return result;
    }

    private static void checkTestResult(Map<String, Boolean> testResult) {
        int countValidTests = 0;
        int countFaultTests = 0;

        OutputMessageUtility.outputString("Test execute result:");
        for (Map.Entry<String, Boolean> test : testResult.entrySet()) {
            if (Boolean.TRUE.equals(test.getValue())) {
                countValidTests++;
                OutputMessageUtility.outputString("Test " + test.getKey() + " is valid");
            }
            else {
                countFaultTests++;
                OutputMessageUtility.outputString("Test " + test.getKey() + " is fault");
            }
        }
        OutputMessageUtility.outputString("Count valid tests:" + countValidTests);
        OutputMessageUtility.outputString("Count fault test:" + countFaultTests);
    }
}
