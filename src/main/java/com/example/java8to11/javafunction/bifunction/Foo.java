package com.example.java8to11.javafunction.bifunction;

import java.util.function.BiFunction;

public class Foo {
    public static void main(String[] args) {
        BiFunction<Integer, String, String> makeKey = (i, s) -> s + "::" + i;
        int id = 3;
        String name = "sgaSolution";
        String key = makeKey.apply(id, name);
        System.out.println(key);
    }
}
