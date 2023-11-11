package com.example.java8to11.javafunction.function_combination;

import java.util.function.Function;

public class FunctionCombinationTest {
    public static void main(String[] args) {
        Function<Integer, Integer> plus10 = (i) -> i + 10;
        Function<Integer, Integer> multiply2 = (i) -> i * 2;
        System.out.println(multiply2.apply(1));
        // 나는 10을 더하기 전에, 2를 곱하겠다.
        //compose : 입력값을 가지고, 뒤의 함수에 적용을하고, 그 나온 결과값을 plus10에 적용한다.
        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);
        System.out.println(multiply2AndPlus10.apply(2));
        // 앞의 function뒤에 연산을 덧붙인다.
        Function<Integer, Integer> plus10AndMultiply2 = plus10.andThen(multiply2);
        System.out.println(plus10AndMultiply2.apply(2));
    }
}
