package com.example.java8to11.method_reference;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {
    public static void main(String[] args) {
        Function<Integer, String> intToString = (i) -> "number";
        // 같은 형식의 static 메소드를 메소드 레퍼런스로 사용할 수 있다.
        UnaryOperator<String> hi = (s) -> "hi " + s;
        UnaryOperator<String> hiCopy = Greeting::hi;
        System.out.println(hiCopy.apply("gangmin"));
        // 인스턴스 메소드를 사용하려면?
        Greeting greeting = new Greeting();
        UnaryOperator<String> helloCopy = greeting::hello;
        // 생성자메소드 레퍼런스
        Supplier<Greeting> newGreeting = Greeting::new;
        // 이렇게 호출해야 메소드가 실행된다.
        Greeting instance = newGreeting.get();
        //매개변수가 있는 생성자, 입력값 1개, 리턴값 1개 -> Function
        Function<String, Greeting> keesunGreeting = Greeting::new;
        // 위와 같지만 다른 메소드를 참조하고 있다.
        Greeting gangmin = keesunGreeting.apply("gangmin");
        System.out.println(gangmin.getName());

        // 임의의 객체의 인스턴스의 인스턴스 메소드 참조
        String[] names = {"keesun", "whiteship", "toby"};
        // 람다를 넣을 수 있다는 것은 메소드 레퍼런스를 넣을 수 있다는 것
        // 따라서 두개의 값을 받아서 하나의 결과를 리턴하는 메소드의 레퍼런스를 매개변수로 넣을 수 있다.
        // static한 메소드가 아니라, 임의의 객체의 인스턴스 메소드를 참조한다.
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));
    }
}
