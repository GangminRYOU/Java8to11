package com.example.java8to11.callable_future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Foo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService threadPoolService = Executors.newFixedThreadPool(4);
        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "Java";
        };

        Callable<String> keesun = () -> {
            Thread.sleep(1000L);
            return "Keesun";
        };
        //한꺼번에 보낼 수 있다.
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, keesun));
        for (Future<String> f : futures) {
            log.info("f={}", f.get());
        }
        String s = threadPoolService.invokeAny(Arrays.asList(hello, java, keesun));
        log.info("s={}", s);
        /**
         * 이렇게 하면 keesun이 나와야 정상인데 Single 스레드나 스레드 풀에 2개밖에 없다면 hello가 먼저 출력된다.
         * 왜냐면 스레드 할당되기 위해 keesun이 기다리기 때문이다.
         */
        executorService.shutdown();
    }
}
