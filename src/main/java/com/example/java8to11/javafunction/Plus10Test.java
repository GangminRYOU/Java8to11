package com.example.java8to11.javafunction;

import java.util.function.Function;

public class Plus10Test {
    public static void main(String[] args) {
        Plus10 plus10 = new Plus10();
        Integer result = plus10.apply(1);
        System.out.println(result);

        Function<Integer, Integer> plus10Ex = (number) -> number + 10;
        Integer result2 = plus10Ex.apply(1);
        System.out.println("result : " + result);

        System.out.println("result2 : " + result2);
    }
}
