package ru.otus;

public class JobStarter {
    public static void run() {
        var job = new Job();
        int startCount = 1;
        String prefixThreadName = "Thread-";
        String primaryOrder = "1";

        new Thread(() -> job.action(startCount, prefixThreadName, primaryOrder)).start();
        new Thread(() -> job.action(startCount, prefixThreadName, primaryOrder)).start();
    }
}
