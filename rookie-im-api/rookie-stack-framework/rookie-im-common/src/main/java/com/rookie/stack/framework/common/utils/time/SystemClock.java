package com.rookie.stack.framework.common.utils.time;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Classname SystemClock
 * @Description TODO
 * @Date 2024/9/25 17:03
 * @Created by liujiapeng
 */
public class SystemClock {
    private static final String THREAD_NAME = "system.clock";
    private static final SystemClock MILLIS_CLOCK = new SystemClock(1);
    private final long precision;
    private final AtomicLong now;
    private SystemClock(long precision) {
        this.precision = precision;
        now = new AtomicLong(System.currentTimeMillis());
        scheduleClockUpdating();
    }
    public static SystemClock millisClock() {
        return MILLIS_CLOCK;
    }
    private void scheduleClockUpdating() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, THREAD_NAME);
            thread.setDaemon(true);
            return thread;
        });
        scheduler.scheduleAtFixedRate(() ->
                now.set(System.currentTimeMillis()), precision, precision, TimeUnit.MILLISECONDS);
    }
    public long now() {
        return now.get();
    }
}