package com.example.java8to11.executors;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        /* Executor가 Thread를 만들고, 우리는 Runnable만 제공해주면 된다.
         */
        ScheduledExecutorService scheduledExecutorService;
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Executor executor;

        //Thread를 하나만 쓰는 Executor
        ExecutorService executorServiceOne = Executors.newSingleThreadExecutor();
        executorServiceOne.execute(() -> {
            log.info("Thread={}", Thread.currentThread().getName());
        });
        executorServiceOne.submit(() -> {
            log.info("Thread={}", Thread.currentThread().getName());
        });
        /**
         * 주의! -> ExecutorService는 작업을 실행하고나면,
         * 다음 작업이 들어올때 까지 대기하기 때문에, Process가 자동으로 죽지 않는다.
         * 따라서, shutdown()으로 명시적으로 꺼줘야한다.
         */
        //GracefulShutdown이다. -> 현재 진행중인 작업은 끝까지 마치고 끝낸다.
        executorServiceOne.shutdown();
        //진행 작업을 기다리지 않고, 바로 죽인다. -> 진행 작업이 끝날지 안끝날지 Guarantee하지 않는다.
        executorService.shutdownNow();
    }
}
