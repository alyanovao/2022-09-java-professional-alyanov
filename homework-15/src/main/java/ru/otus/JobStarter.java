package ru.otus;

public class JobStarter {
    public static void run() {
        var job = new Job();
        int startCount = 1;
        String primaryOrder = "0";

        new Thread(() -> job.action(startCount, primaryOrder)).start();
        new Thread(() -> job.action(startCount, primaryOrder)).start();
    }
}
