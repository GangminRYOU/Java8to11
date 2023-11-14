package com.example.java8to11.callable_future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> hello = () -> {
            Thread.sleep(3000L);
            return "Hello";
        };
        //Callable의 return값으로 Future를 반환한다.
        Future<String> helloFuture = executorService.submit(hello);
        log.info("isDone?={}", helloFuture.isDone());
        System.out.println("get()이전까지는 그대로 실행이 된다.");
        //하지만 get()을 만나는 순간 멈추고 Blocking이 된다. -> Blocking Call
        helloFuture.get();
        log.info("isDone?={}", helloFuture.isDone());
        System.out.println("End!!");
        executorService.shutdown();
    }

}
