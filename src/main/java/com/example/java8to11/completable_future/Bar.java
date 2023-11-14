package com.example.java8to11.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bar {
    /**
     * 몇초 이내에 응답이 오지 않으면, 기본값이나, 캐싱해둔 값을 응답하도록 할 수 있다.
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //CompletableFuture는 더이상 Executor나 그런 것들을 사용할 필요가 없다.
        CompletableFuture<String> future = new CompletableFuture<>();
        //future의 값 지정
        future.complete("keesun");
        log.info(future.get());

        log.info("case1()={}", case1());
        log.info("runAsync()");
        runAsync();
        log.info("supplyAsync()");
        supplyAsync();
        log.info("callBack()");
        callBack();
        log.info("callBackWithoutReturn");
        callBackWithoutReturn();
        log.info("noReturn()");
        noReturn();
        log.info("customThreadPool()");
        customThreadPool();
    }

    public static CompletableFuture<String> case1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.completedFuture("keesun");
        log.info(future.get());
        return future;
    }

    public static void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            log.info("Hello! " + Thread.currentThread().getName());
        });
        //
        future.get();
        //join을하면, Unchecked Exception으로 알아서 예외처리를 해준다.
        future.join();
    }

    public static void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            log.info("Hello " + Thread.currentThread().getName());
            return "supplied hello";
        });
        log.info("return={}", future.get());
    }

    /**
     * 여기까지는 그냥 Future를 사용하는 것과 같다.
     * 하지만 CompletableFuture는 결과가 왔을때 Asynchronous하게 수행하는 Callback을 지정할 수 있다.
     */
    public static void callBack() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            log.info("Hello! {}", Thread.currentThread().getName());
            return "Hello!!! - OpenApi Result";
        }).thenApply((s) -> {
            log.info(Thread.currentThread().getName());
            return s.toUpperCase();
        });
        log.info(future.get());
    }

    public static void callBackWithoutReturn() throws ExecutionException, InterruptedException {
        //Consumer가 들어온다.
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            log.info("Hello! {}", Thread.currentThread().getName());
            return "Hello!! - OpenApi Result";
        }).thenAccept((s) -> {
            log.info(Thread.currentThread().getName());
            log.info(s.replace("OpenApi", "Kakao"));
        });
        future.get();
    }
    public static void noReturn() throws ExecutionException, InterruptedException {
        //Consumer가 들어온다.
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            log.info("Hello! {}", Thread.currentThread().getName());
            return "Hello!! - OpenApi Result";
        //그냥 Runnable이 온다.
        }).thenRun(() -> {
            log.info(Thread.currentThread().getName());
        });
        future.get();
    }

    /**
     * 어떻게 ThreadPool도 없이 이렇게 작업이 가능할까?
     * ForkJoinPool을 내부적으로 사용하기 때문이다.
     * ForkJoinPool은 내부적으로 Dequeue를 사용한다. -> 마지막에 들어온게 먼저 나가는 자료구조?
     * 자기 쓰레드가 할일이 없으면, 쓰레드가 직접 Dequeue에서 작업을가져와서 작업하는 방식의 자료구조
     * 작업단위를 compute()와 join해서 쪼개서 처리도 한다.
     * 그리고 forkJoinPool은 내부에 CommonPool이라는 ThreadPool을 가지고 있다.
     */

    //하지만 우리가 원한다면, 얼마든지 ThreadPool을 만들어서 붙일 수 도 있다.
    public static void customThreadPool(){
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            log.info("Hello!! {}", Thread.currentThread().getName());
            return "Hello~ - Amazon API";
        }, executorService).thenRun(() -> {
            log.info(Thread.currentThread().getName());
        });
        executorService.shutdown();
    }
}
