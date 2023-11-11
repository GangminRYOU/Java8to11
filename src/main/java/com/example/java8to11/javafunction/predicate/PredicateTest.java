package com.example.java8to11.javafunction.predicate;

import java.util.function.Predicate;

public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> startsWithKeesun = (s) -> s.startsWith("keesun");
        Predicate<Integer> isEven = (i) -> i % 2 == 0;
        System.out.println(startsWithKeesun.test("keesun"));
    }
}
