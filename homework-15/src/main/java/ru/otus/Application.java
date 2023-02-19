package ru.otus;

public class Application {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        //JobStarter.run();
        PoolJobStarter.run();
    }
}