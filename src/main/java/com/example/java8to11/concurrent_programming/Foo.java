package com.example.java8to11.concurrent_programming;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Foo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {

                log.info("Thread={}", Thread.currentThread().getName());
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }

        });
        thread.start();
        //main thread
        log.info("Hello={}", Thread.currentThread().getName());
        //main thread가 thread를 기다리게 하기
        try{
            thread.join();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }

        //join을 함으로써 대기해서 위의 Thread={}가 찍히기를 기다린다.
        /**
         * 이렇게 join으로 대기를 하는 도중에 interrupt가 발생해도 InterruptedException이 발생한다.
         * 사실 이렇게 Thread가 2개만 되도 매우 복잡해지고, 개발자가 관리하기 힘들어진다.
         * 그래서 나온게 Executor고 Executor를 사용할 수 있게 되면, Future를 사용할 수 있게 되고,
         * Future를 사용할 수 있게 되면, CompletableFuture를 사용할 수 있게 된다.
         */
        log.info("{} is finished", thread);
    }
}
