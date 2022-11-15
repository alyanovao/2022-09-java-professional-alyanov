package ru.otus.proxy;

import ru.otus.annotations.Log;
import ru.otus.exception.ApplicationException;
import ru.otus.util.OutputMessageUtility;
import ru.otus.util.ReflectionUtility;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MyInvocationHandler implements InvocationHandler {

    private Object instance;
    private Map<Method, Method> equalMethods = new HashMap<>();

    public MyInvocationHandler(Object instance) {
        this.instance = instance;
        Method[] classMethods = instance.getClass().getDeclaredMethods();
        Method[] interfaceMethods = instance.getClass().getInterfaces()[0].getDeclaredMethods();
        for (Method method : interfaceMethods) {
            Method methodClass = getMethodByImplementation(method, classMethods);
            equalMethods.put(method, methodClass);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
        Method newMethod = equalMethods.get(method);
        if (ReflectionUtility.hasAnnotation(newMethod, Log.class)) {
            OutputMessageUtility.outputString("execute method: " + method.getName() + ", param " + method.getParameterTypes().length);
        }
        return method.invoke(instance, objects);
    }

    public static Method getMethodByImplementation(Method methodInterface, Method[] classMethods) {
        Object[] interfaceParameters = methodInterface.getParameterTypes();

        for (Method methodOfClass : classMethods) {
            Object[] methodParameters = methodOfClass.getParameterTypes();
            int compareParamsCount = 0;
            for (int i = 0; i < methodParameters.length; i++) {
                if (interfaceParameters.length != methodParameters.length) {
                    break;
                } else if (!methodParameters[i].getClass().isPrimitive() && interfaceParameters[i].getClass().isInstance(methodParameters[i].getClass())) {
                    compareParamsCount++;
                }
            }
            if (compareParamsCount == methodParameters.length) {
                return methodOfClass;
            }
        }
        throw new ApplicationException();
    }
}
