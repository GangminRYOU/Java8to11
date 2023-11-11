package com.example.java8to11.lambda;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public class Foo {
    public static void main(String[] args) {
        Supplier<Integer> get10 = () -> 10;
        BiFunction<Integer, Integer, Integer> func = (a, b) -> a + b;
        BinaryOperator<Integer> func2 = (a, b) -> a * b;
    }
}
