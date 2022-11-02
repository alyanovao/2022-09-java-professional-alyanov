package ru.otus.service;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.exception.ApplicationException;
import ru.otus.util.OutputMessageUtility;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

public class ClassStarterService {

    private ClassStarterService() {
        throw new ApplicationException("This is Utility class");
    }

    private static final ReflectionService service = new ReflectionServiceImpl();

    public static void run(String className) {
            try {
                Class<?> clazz = service.getClassInstance(className);
                Map<String, Boolean> testResult = runTest(clazz);
                checkTestResult(testResult);
            }
            catch (Exception e) {
                OutputMessageUtility.outputString("Exception start test class :: " + e.toString());
            }
        }

    private static List<Method> getListMethodWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return Arrays.stream(clazz.getMethods())
                .filter(method -> service.hasAnnotation(method, annotationClass))
                .peek(method -> OutputMessageUtility.outputString(method.getName() + " has annotation " + annotationClass.getSimpleName()))
                .toList();
    }

    private static Optional<Method> getMethodWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        List<Method> methods = Arrays.stream(clazz.getMethods())
                .filter(method -> service.hasAnnotation(method, annotationClass))
                .peek(method -> OutputMessageUtility.outputString(method.getName() + " has annotation " + annotationClass.getSimpleName()))
                .toList();
        if (methods.size() > 1) {
            throw new ApplicationException("Methods with annotation " + annotationClass.getSimpleName() + " more one");
        }
        return methods.isEmpty() ? Optional.empty() : Optional.ofNullable(methods.get(0));
    }

    private static Map<String, Boolean> runTest(Class<?> clazz) {
        Map<String, Boolean> testResult = new HashMap<>();

        //В классе только один не обязательный метод before и after, с другой реализацией не сталкивался
        Optional<Method> beforeMethod = getMethodWithAnnotation(clazz, Before.class);
        Optional<Method> afterMethod = getMethodWithAnnotation(clazz, After.class);
        List<Method> testMethods = getListMethodWithAnnotation(clazz, Test.class);

        List<Method> randomMethods = new ArrayList<>(testMethods);
        Collections.shuffle(randomMethods);

        testMethods.forEach(testMethod -> {
            OutputMessageUtility.outputString("----------------------------------");
            Object object = service.getNewClassInstance(clazz, null);

            boolean executeBefore = true;
            if (beforeMethod.isPresent()) {
                executeBefore = service.callMethod(object, beforeMethod.get().getName());
            }

            if (executeBefore) {
                testResult.put(testMethod.getName(), service.callMethod(object, testMethod.getName()));
            }

            if (afterMethod.isPresent()) {
                service.callMethod(object, afterMethod.get().getName());
            }
        });
        return testResult;
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
