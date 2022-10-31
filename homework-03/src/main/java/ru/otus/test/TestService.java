package ru.otus.test;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.math.BigDecimal;

public class TestService {
    @Before
    public void setUp() {
        System.out.println("Вызываем @Before");
    }

    @Test
    public void testMethod1(String message) {
        System.out.println(String.format("Вызываем @Test и выводим сообщение: %",message));
    }

    @Test
    public void testMethod2(String message, BigDecimal digit) {
        System.out.println(String.format("Вызываем @Test и выводим сообщение: % и число: %",message, digit));
    }

    @Test
    public void testMethod3() {
        System.out.println(String.format("Вызываем @Test"));
    }

    @After
    public void shootDown() {
        System.out.println("Вызываем @After");
    }
}
