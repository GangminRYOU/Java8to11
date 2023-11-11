package com.example.java8to11.javafunction.unary;

import java.util.function.UnaryOperator;

public class Foo {
    public static void main(String[] args) {
        // 입력값과 결과값의 타입이 같은 경우, 입력값이 하나인 경우에 사용
        UnaryOperator<Integer> plus10 = (i) -> i + 10;
        System.out.println(plus10.apply(10));
    }
}
