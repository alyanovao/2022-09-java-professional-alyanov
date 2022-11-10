package ru.otus.proxy;

import ru.otus.annotations.Log;
import ru.otus.service.TestLoggingService;
import ru.otus.util.OutputMessageUtility;
import ru.otus.util.ReflectionUtility;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    private TestLoggingService serviceClass;

    public MyInvocationHandler(TestLoggingService serviceClass) {
        this.serviceClass = serviceClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
        String methodImplementationName = serviceClass.getClass().getName();
        Method newMethod = ReflectionUtility.getMethodImplementationByInterface(methodImplementationName, method);
        if (ReflectionUtility.hasAnnotation(newMethod, Log.class)) {
            OutputMessageUtility.outputString("execute method: " + method.getName() + ", param " + method.getParameterTypes().length);
        }
        return method.invoke(serviceClass, objects);
    }
}
