package com.example.java8to11.basic_api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bar_Comparator {
    public static void main(String[] args) {
        List<String> name = new ArrayList<>();
        name.add("keesun");
        name.add("whiteship");
        name.add("toby");
        name.add("foo");
        name.add("var");

        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        name.sort(compareToIgnoreCase.reversed().thenComparingInt(String::length));

        //WebMvcConfigurer가 abstract class대신 default 메소드를 활용해 부분적으로 상속받을 수 있게 함
    }
}
