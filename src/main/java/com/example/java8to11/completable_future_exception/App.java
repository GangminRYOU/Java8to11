package com.example.java8to11.completable_future_exception;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    /**
     * @NOTE
     * 이전에는 Future만 가지고 예외처리를 하는것이 힘들었다.
     * 비동기 작업 두개를 연결하는것 자체가 callback없이 사용하기 힘들었다.
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        asyncTwo();
        getAppleStockPriceAndMSStockPrice();
        allOf();
        anyOf();
    }

    public static void asyncTwo() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("Hello~={}", Thread.currentThread().getName());
            return "hello";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            log.info("World~={}", Thread.currentThread().getName());
            return "world!!";
        });

        //예전에 Blocking Call -> future1을 기다렸다가 2를 받을수 있다.
        future1.get();
        future2.get();
        //그게 아니라 이젠 비동기 동작 2개를 자연스럽게 이어 붙일수있다.
        //두 Future간에 의존성이 있는 경우
        CompletableFuture<String> future = future1.thenCompose(App::getWorld);
        log.info(future.get());
    }

    public static CompletableFuture<String> getWorld(String message){
        return CompletableFuture.supplyAsync(() -> {
            log.info("Wolrd!" + Thread.currentThread().getName());
            return message + "World!";
        });
    }

    //두 Future가 연관관계가 없는 경우 -> Apple주식 가격과 MS주식 가격을 모두 가져와서 무언가 하는 경우
    //이 경우에는 두 주식 가격간에 의존성이 없다.
    public static void getAppleStockPriceAndMSStockPrice() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> applePrice = CompletableFuture.supplyAsync(() -> {
            log.info("Waiting for Apple Stock Price at:{}", System.currentTimeMillis());
            return new Random().nextInt();
        });
        CompletableFuture<Integer> msPrice = CompletableFuture.supplyAsync(() -> {
            log.info("Waiting for MS Stock Price at:{}", System.currentTimeMillis());
            return new Random().nextInt();
        });
        //두개의 매개변수를 받아서 하나의 값을 도출한다. -> BiFunction
        CompletableFuture<String> stock = applePrice.thenCombine(msPrice,
            (apple, ms) -> "apple price: " + apple + ", ms price: " + ms);
        log.info("증시={}", stock.get());
    }

    /**
     * 두개 이상의 task를 모두 합쳐서 실행하는 방법
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void allOf() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> applePrice = CompletableFuture.supplyAsync(() -> {
            log.info("Waiting for Apple Stock Price at:{}", System.currentTimeMillis());
            return new Random().nextInt(300);
        });
        CompletableFuture<Integer> msPrice = CompletableFuture.supplyAsync(() -> {
            log.info("Waiting for MS Stock Price at:{}", System.currentTimeMillis());
            return new Random().nextInt(300);
        });
        //문제는 모든 task의 결과가 동일한 타입이라는 보장도 없고, 그중에 하나는 에러가 날 확률도 있다.
        CompletableFuture<Void> future = CompletableFuture.allOf(applePrice, msPrice)
            .thenAccept((result) -> {
                //사실상 결과가 무의미 하다. -> 사실 이렇게 하면 결과가 null이다.
            });

        //따라서 아래의 방법으로, 결과값을 비동기적으로 blocking없이 처리해야한다.
        List<CompletableFuture<Integer>> completableFutures = Arrays.asList(applePrice, msPrice);
        CompletableFuture[] futuresArray = completableFutures.toArray(new CompletableFuture[completableFutures.size()]);
        CompletableFuture<Integer> averageStockPrice = CompletableFuture.allOf(applePrice, msPrice)
            //thenApply가 호출되는 시점은 이미 completableFutures에 모든 결과값이 들어온 상태이다.
            .thenApply(v -> completableFutures.stream()
                .map(f -> f.join())
                .reduce(0, (o1, o2) -> (o1 + o2) / completableFutures.size()));
        //이렇게 하면 아무것도 blocking이 되지 않는다.
        Integer price = averageStockPrice.get();
        log.info("average stock price={}", price);
    }

    /**
     * @Note : 아무거나 하나 결과 받기
     */
    public static void anyOf() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> applePrice = CompletableFuture.supplyAsync(() -> {
            log.info("Waiting for Apple Stock Price at:{}", System.currentTimeMillis());
            return new Random().nextInt(300);
        });
        CompletableFuture<Integer> msPrice = CompletableFuture.supplyAsync(() -> {
            log.info("Waiting for MS Stock Price at:{}", System.currentTimeMillis());
            return new Random().nextInt(300);
        });

        //TODO
        CompletableFuture<Void> future = CompletableFuture.anyOf(applePrice, msPrice)
            .thenAccept((i) -> log.info("stock price of apple or ms={}", i));
        future.get();
    }
}
