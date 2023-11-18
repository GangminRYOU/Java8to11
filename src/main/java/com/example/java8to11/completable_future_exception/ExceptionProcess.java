package com.example.java8to11.completable_future_exception;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionProcess {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        boolean throwError = true;
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalStateException();
            }
            log.info("Hello {}", Thread.currentThread().getName());
            return "hi, it's me again. I'm back";
        }).exceptionally(ex -> {
            log.info("cause={}", ex.getCause());
            return "Error!";
        });

        log.info("hello.get()={}", hello.get());
        handle();
    }


    public static void handle() throws ExecutionException, InterruptedException {
        boolean throwError = true;
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalStateException("handle() error");
            }
            log.info("Hello {}", Thread.currentThread().getName());
            return "hi, it's me again. I'm back";
            //handle은 정상적인 경우와, 비정상적인 경우에 모두 사용할 수 있는데, 매개변수로 BiFuction이 들어온다.
        }).handle((rs, ex) -> {
            if(ex != null){
                log.info("ex={}", ex.getMessage());
                return "ERROR!!!!!!!!!!";
            }
            return rs;
        });

        log.info("hello.get()={}", hello.get());
    }
}
