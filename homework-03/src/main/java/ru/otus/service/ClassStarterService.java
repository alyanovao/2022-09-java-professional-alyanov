package ru.otus.service;

import ru.otus.util.OutputMessageUtility;

import java.lang.reflect.Method;
import java.util.*;

public class ClassStarterService {

    private static final ReflectionService service = new ReflectionServiceImpl();

    public static void run(String className) {
            try {
                Class<?> clazz = service.getClassInstance(className);

                //В классе только один не обязательный метод before и after, с другой реализацией не сталкивался
                Optional<Method> beforeMethod = Arrays.stream(clazz.getMethods())
                        .filter(service::hasBefore)
                        .map(method -> {
                            OutputMessageUtility.outputString(method.getName() + " has before annotation");
                            return method;
                        })
                        .findFirst();

                Optional<Method> afterMethod = Arrays.stream(clazz.getMethods())
                        .filter(service::hasAfter)
                        .map(method -> {
                            System.out.println(method.getName() + " has after annotation");
                            return method;
                        })
                        .findFirst();

                List<Method> testMethods = Arrays.stream(clazz.getMethods())
                        .filter(service::hasTest)
                        .map(method -> {
                            OutputMessageUtility.outputString(method.getName() + " has test annotation");
                            return method;
                        })
                        .toList();

                List<Method> randomMethods = new ArrayList<>(testMethods);
                Collections.shuffle(randomMethods);
                Map<String, Boolean> testResult = runTest(clazz, beforeMethod, randomMethods, afterMethod);
                checkTestResult(testResult);
            }
            catch (Exception e) {
                OutputMessageUtility.outputString("Exception start test class :: " + e.toString());
            }
        }

        private static Map<String, Boolean> runTest(Class<?> clazz, Optional<Method> beforeMethod, List<Method> testMethods,
                                                    Optional<Method> afterMethod) {
            Map<String, Boolean> testResult = new HashMap<>();

            testMethods.stream().forEach(testMethod -> {
                OutputMessageUtility.outputString("----------------------------------");
                Object object = service.getNewClassInstance(clazz, null);

                if (beforeMethod.isPresent()) {
                    service.callMethod(object, beforeMethod.get().getName());
                }

                testResult.put(testMethod.getName(), service.callMethod(object, testMethod.getName()));

                if (afterMethod.isPresent()) {
                    service.callMethod(object, afterMethod.get().getName());
                }
            });
            return testResult;
        };

    private static void checkTestResult(Map<String, Boolean> testResult) {
        int countValidTests = 0;
        int countFaultTests = 0;

        OutputMessageUtility.outputString("Test execute result:");
        for (Map.Entry<String, Boolean> test : testResult.entrySet()) {
            if (test.getValue()) {
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
