package com.example.java8to11.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bar {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();

        //반복적으로 찍는 경우
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(getRunnable("Hello"), 1, 2, TimeUnit.SECONDS);
        //ForkJoinFramework이라는 것도 있다.
        //Future는 Return type이 Void가 아니라 결과가 있는 경우, Runnable을 사용할 수 없다.
        //Callable을 사용하면 값을 Return할 수 있다.
        //Return값을 받아오는 무언가가 바로 Future다.
    }

    private static Runnable getRunnable(String str) {
        return () -> {
            log.info("-------------------------------------");
            log.info("Method Call by={}", Thread.currentThread().getName());
            log.info("Message={}", str);
        };
    }
}
