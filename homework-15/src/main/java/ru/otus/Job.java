package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Job {
    private final Logger log = LoggerFactory.getLogger(Job.class);

    boolean directionCount = true;
    static String lastThreadName = "";
    static String firstThreadName;

    public synchronized void action(int startCount, String prefixThreadName, String threadOrder) {
        int count = startCount;
        firstThreadName = prefixThreadName + threadOrder;

        try {

            if (!firstThreadName.equals(Thread.currentThread().getName())) {
                notifyAll();
                this.wait();
            }
            while (!Thread.currentThread().isInterrupted()) {
                    while (lastThreadName.equals(Thread.currentThread().getName())) {
                        lastThreadName = Thread.currentThread().getName();
                        notifyAll();
                        this.wait();
                    }
                    log.info("count=" + count);
                    lastThreadName = Thread.currentThread().getName();
                    sleep();
                    setDirectionCount(count);
                    count = changeCounter(directionCount, count);
                    notifyAll();
            }
        }
        catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private int changeCounter(boolean directionCount, int count) {
        if (directionCount) {
            count++;
        } else {
            count--;
        }
        return count;
    }

    private void setDirectionCount(int count) {
        if (count == 10) {
            directionCount = false;
        }
        if (count == 1) {
            directionCount = true;
        }
    }

    private void sleep() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        }
        catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();;
        }
    }
}
