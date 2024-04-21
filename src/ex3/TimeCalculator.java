package ex3;

import java.time.Duration;
import java.time.Instant;

public class TimeCalculator implements Runnable {
    private Instant startTime;
    private Instant endTime;

    public void run() {
        startTime = Instant.now();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Error wtf  |  " + e.getMessage());
            e.printStackTrace();
        }
        endTime = Instant.now();
        Duration timeElapsed = Duration.between(startTime, endTime);
        System.out.println("Time taken: " + timeElapsed.toNanos() + " nanoseconds");
    }
}
