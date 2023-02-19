package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Job {
    private final Logger log = LoggerFactory.getLogger(Job.class);

    boolean directionCount = true;
    String lastThreadName = "";
    String firstThreadName;

    public synchronized void action(int startCount, String prefixThreadName, String threadOrder) {
        log.info("start :: " + Thread.currentThread().getName());
        int count = startCount;
        firstThreadName = prefixThreadName + threadOrder;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (firstThreadName != null && !firstThreadName.equals(Thread.currentThread().getName())) {
                    this.wait();
                } else {
                    firstThreadName = null;
                }
                while (lastThreadName.equals(Thread.currentThread().getName())) {
                    this.wait();
                }
                log.info("count=" + count);
                lastThreadName = Thread.currentThread().getName();
                sleep();
                setDirectionCount(count);
                count = changeCounter(directionCount, count);
                notifyAll();
            }
            catch (InterruptedException e) {
                log.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
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
