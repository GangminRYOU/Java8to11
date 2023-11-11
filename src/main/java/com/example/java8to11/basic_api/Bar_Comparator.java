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
    }
}
