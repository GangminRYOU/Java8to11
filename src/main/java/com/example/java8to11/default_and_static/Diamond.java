package com.example.java8to11.default_and_static;

public interface Diamond {
    default void printNameUpperCase(){
        // 구체 클래스가 구현한 메소드를 호출
        System.out.println("Bar");
    }
}
