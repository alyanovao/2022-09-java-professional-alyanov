package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;
import ru.otus.appcontainer.exception.ApplicationException;
import ru.otus.appcontainer.exception.NoFoundMethodException;
import ru.otus.appcontainer.exception.BeanLoadException;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);

        try {
            var configInstance = configClass.getConstructor().newInstance();
            var methods = Arrays.stream(configClass.getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(AppComponent.class))
                    .sorted(Comparator.comparingInt(method -> method.getAnnotation(AppComponent.class).order()))
                    .toList();

            for (var method : methods) {
                try {
                    String name = method.getAnnotation(AppComponent.class).name();
                    var parameters = Arrays.stream(method.getParameterTypes()).map(type -> getAppComponent(type)).toList();
                    var bean = method.invoke(configInstance, parameters.toArray());
                    if (appComponentsByName.containsKey(name)) {
                        throw new BeanLoadException("Constraint unique bean");
                    }
                    appComponentsByName.put(name, bean);
                    appComponents.add(bean);
                }
                catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                    throw new BeanLoadException(e);
                }
            }

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new NoFoundMethodException(e);
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        if (appComponents.stream()
                .filter(component -> Arrays.stream(component.getClass().getInterfaces())
                .allMatch(component1 -> component1 == componentClass))
                .toList().size() > 1) {
            throw new BeanLoadException("found more one same bean");
        }
        for(var component : appComponents) {
            if (Arrays.stream(component.getClass().getInterfaces()).anyMatch(c -> c == componentClass)) {
                return (C) component;
            }
            if (component.getClass() == componentClass) {
                return (C) component;
            }
        }
        throw new ApplicationException("not found component");
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) appComponentsByName.get(componentName);
    }
}
