package com.example.java8to11.default_and_static;

public class App {
    public static void main(String[] args) {
        Foo foo = new DefaultFoo("gangmin");
        foo.printName();
        // 인터페이스를 구현한 모든 인스턴스가 이 기능을 가지게 된다.
        foo.printNameUpperCase();
        // BUT! 이 기능이 항상 제데로 동작할 거란 보장이 없다. -> NULL이 오면? 에러가 나온다.
        // 따라서, 주석처리 (@ImplSpec)등을 이용해 잘 문서화해야한다.
        Foo.printAnything();
    }
}
