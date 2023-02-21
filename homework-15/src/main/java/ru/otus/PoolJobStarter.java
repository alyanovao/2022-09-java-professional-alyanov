package ru.otus;

import java.util.concurrent.Executors;

public class PoolJobStarter {
    public static void run() {
        var job = new Job();
        int startCount = 1;
        String primaryOrder = "1";

        var executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> job.action(startCount, primaryOrder));
        executor.submit(() -> job.action(startCount, primaryOrder));
    }
}
