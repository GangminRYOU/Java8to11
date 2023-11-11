package com.example.java8to11.default_and_static;

/** @Case 만약, Foo와 같은 default 메소드를 가지는 Bar까지 제공한다면?
 *  -> 두 메소드가 충돌하면서 컴파일 에러가 발생한다.
 */

public class DefaultFoo implements Foo, Bar{
    String name;
    @Override
    public void printName() {
        System.out.println(name);
    }

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    // 이렇게 재정의 할 수도 있다.
    // @Override
    // public void printNameUpperCase() {
    //     Foo.super.printNameUpperCase();
    // }

    //default가 충돌하는 경우, 직접 Overriding을 해야한다.

    @Override
    public void printNameUpperCase() {

    }
}
