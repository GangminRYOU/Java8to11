package com.example.java8to11.javafunction.supplier;

import java.util.function.Supplier;

public class SupplierTest {
    public static void main(String[] args) {
        Supplier<Integer> get10 = () -> 19;
        System.out.println(get10.get());
    }
}
