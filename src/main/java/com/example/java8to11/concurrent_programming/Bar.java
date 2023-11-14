package com.example.java8to11.concurrent_programming;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bar {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                log.info("Thread={}", Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    log.info("interrupted!");
                    //return;
                }
            }
        });
        thread.start();
        log.info("Hello={}", Thread.currentThread().getName());
        Thread.sleep(3000L);
        // interrupt로 thread를 깨운다.
        thread.interrupt();
    }
}
