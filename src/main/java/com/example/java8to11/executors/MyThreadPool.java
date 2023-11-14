package com.example.java8to11.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyThreadPool {
    public static void main(String[] args) {
        //사실 ExecutorService안에 ThreadPool이 있다.
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            log.info("Thread={}", Thread.currentThread().getName());
        });
        //ThreadPool에서 Thread 2개가 번갈아가며 작업을 수행한다.
        executorService.submit(getRunnable("Hello"));
        executorService.submit(getRunnable("Keesun"));
        executorService.submit(getRunnable("The"));
        executorService.submit(getRunnable("Java"));
        executorService.submit(getRunnable("Thread"));
        executorService.shutdown();
    }

    private static Runnable getRunnable(String str) {
       return () -> {
           log.info("-------------------------------------");
           log.info("Method Call by={}", Thread.currentThread().getName());
           log.info("Message={}", str);
       };
    }
}
