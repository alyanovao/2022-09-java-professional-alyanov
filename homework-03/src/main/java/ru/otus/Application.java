package ru.otus;

import ru.otus.service.ClassStarterService;
import ru.otus.util.OutputMessageUtility;

public class Application {
    public static void main(String[] args) {
        OutputMessageUtility.outputString("Старт приложения");
        //Запускать вызовом статического метода с именем класса с тестами
        //Если не это требование сделал бы аналогично ReflectionService
        ClassStarterService.run("ru.otus.test.TestService");
        OutputMessageUtility.outputString("Финиш приложения");
    }
}
