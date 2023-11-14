package com.example.java8to11.completable_future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    /**
     * Future의 문제점 : 예외처리용 API를 제공하지 않는다.
     *
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> future = executorService.submit(() -> "hello!");
        //Future로 get을 하기 전까지는 아무것도 할 수가 없다.
        future.get();
        //물론, get()전에 무언가 막 하면 되긴한다.
        //하지만 보통은 Javascript처럼 결과값을 받았을때 Action도 같이 적어줌

    }

}
