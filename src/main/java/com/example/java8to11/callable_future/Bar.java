package com.example.java8to11.callable_future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bar {
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
        //cancle(true)를 주면, 현재 진행중인 작업을 interrupt하면서 종료 <-> false -> 작업을 기다렸다 종료
        //하지만 기다린다 해도, cancle을 호출하면, 값을 받아올 수는 없다.
        helloFuture.cancel(true);
        //cancle을 하면 isDone의 값을 true가 나온다.
        log.info("isDone?={}", helloFuture.isDone());
        //get()하려고 하면, 에러가 난다.
        helloFuture.get();
        System.out.println("End!!");
        executorService.shutdown();
    }
}
