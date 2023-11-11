package com.example.java8to11;

public interface RunSomething {
    abstract void doIt();

    // Java8부터 interface안에 static 메소드 정의가능
    static void printName(){
        System.out.println("Keesun");
    }

    default void printAge(){
        System.out.println("28?");
    }
}
