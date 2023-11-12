package com.example.java8to11.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Parallel {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("keesun");
        names.add("whiteship");
        names.add("toby");
        names.add("foo");
        names.add("var");

        // 병렬처리하기 힘든 코드
        // for (String name : names) {
        //     if(name.startsWith("k")){
        //         System.out.println(name.toUpperCase());
        //     }
        // }
        // Spliterator가 병렬처리를 내부적으로한다.
        System.out.println("-------------------병렬 없이-------------------");
        Long startTime = System.currentTimeMillis();
        List<String> collect = names.parallelStream().map(String::toUpperCase)
            .collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println("Elaped Time: " + (System.currentTimeMillis() - startTime));
        System.out.println("-------------------병렬처리-------------------");
        startTime = System.currentTimeMillis();
        List<String> forkJoin = names.parallelStream().map((s) -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
        //forkJoinPool을 써서 병렬 처리한다.
        forkJoin.forEach(System.out::println);
        // 병렬처리가 다 빠른게 아니다.
        // 유용한 경우는 정말 데이터가 방대한 경우이다.
        System.out.println("Elaped Time: " + (System.currentTimeMillis() - startTime));
    }
}
