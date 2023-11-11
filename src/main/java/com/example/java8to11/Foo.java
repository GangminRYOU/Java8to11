package com.example.java8to11;

import com.example.java8to11.function.ExectionSomething;

public class Foo {
    public static void main(String[] args) {
        // 익명 내부 클래스
        ExectionSomething exectionSomething = new ExectionSomething() {
            @Override
            public void doIt() {

            }
        };
        //람다
        ExectionSomething function = () -> System.out.println("hello Functional Interface!");

        ExectionSomething lambda = () -> {
            System.out.println("hello Functional Interface!");
            System.out.println("lambda");
        };

        function.doIt();
        lambda.doIt();
    }
}
