package com.example.java8to11.default_and_static;

public interface Bar extends Foo{
    /**@Case
     * Bar에서는 Foo가 default 메소드로 제공하는 printNameUpperCase를 제공하고 싶지 않다면?
     */

    //추상메소드로 제공해버리면 된다.
    void printNameUpperCase();
}
