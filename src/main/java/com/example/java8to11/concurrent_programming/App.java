package com.example.java8to11.concurrent_programming;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        //Thread를 만드는 첫번쨰 방법
        MyThread myThread = new MyThread();

        log.info("Hello!");
        //Runnable이 Functional Interface가 되면서 이렇게 Lambda가 가능해짐
        Thread newThread = new Thread(() -> {
            try {
                //현재 스레드 대기 -> 다른 스레드에 작업이 할당된다.
                // -> main Thread가 먼저 작업을 진행함
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                //InterruptedException -> 자는 동안 누가 interrupt()로 깨우면
                throw new RuntimeException(e);
            }
            log.info("Thread: {}", Thread.currentThread().getName());
        });
        newThread.start();
        //Hello Thread
        myThread.start();
    }

    static class MyThread extends Thread{

        @Override
        public void run() {
           log.info("Hello: {}", Thread.currentThread().getName());
        }
    }


}
