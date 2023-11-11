package com.example.java8to11.basic_api;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<String> name = new ArrayList<>();
        name.add("keesun");
        name.add("whiteship");
        name.add("toby");
        name.add("foo");
        name.add("var");

        // Consumer가 들어온다.
        name.forEach(System.out::println);
        System.out.println("--------------------------");
        // 쪼갤수 있는 기능을 가지 Iterator
        Spliterator<String> spliterator = name.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit();
        while(spliterator.tryAdvance(System.out::println));
        System.out.println("======================");
        while (spliterator1.tryAdvance(System.out::println));
        //Stream의 기반에 Spliterator가 있다.
        long k = name.stream().map(String::toUpperCase)
            .filter(s -> s.startsWith("K"))
            .count();
        List<String> k1 = name.stream().map(String::toUpperCase)
            .filter(s -> !s.startsWith("K"))
            .collect(Collectors.toList());
        System.out.println("------------------------------------");
        System.out.println(k);
        System.out.println("====================================");
        System.out.println(k1);
        System.out.println("====================================");
        name.removeIf(s -> s.startsWith("K"));
        name.forEach(System.out::println);
    }
}
