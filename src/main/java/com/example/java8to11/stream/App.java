package com.example.java8to11.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("keesun");
        names.add("whiteship");
        names.add("toby");
        names.add("foo");
        names.add("var");

        // 이렇게 stream처리를 해도 안에 있는 데이터는 대문자로 바뀌지 않는다.
        Stream<String> stringStream = names.stream().map(String::toUpperCase);
        names.forEach(System.out::println);

        //무제한으로 들어오는 데이터를 stream처리 할 수도 있다.

        //중개 Operation은 근본적으로 Lazy하다.
        //map도 중개 Operation
        //중개 operator는 Stream을 return한다.
        System.out.println("--------------출력 되는지?--------------------");
        names.stream().map(s -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());
        // 이렇게 terminal operator가 오면 실행을 한다.
        System.out.println("-------------------------끝-----------------------");
        names.forEach(System.out::println);
        //중개형 operator들은 terminal operator들이 오기 전까지 실행을 하지 않는다.
    }
}
