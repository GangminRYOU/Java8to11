package com.example.java8to11.default_and_static;

public interface Foo {
    void printName();
    // 이 인터페이스를 구현한 모든 클래스에 메소드를 공통적으로 추가하고 싶은 요구사항이 발생했다.
    // 이렇게 하면, 모든 클래스가 메소드를 구현하지 않았기 때문에, 에러가 난다.
    // void printNameUpperCase();

    /**
     * @ImplSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     * 따라서 NULL값을 반환해서는 안된다.
     */
    default void printNameUpperCase(){
        // 구체 클래스가 구현한 메소드를 호출
        System.out.println(getName().toUpperCase());
    }
    // Collection에도 removeIf()라는 default메소드를 사용한다.

    String getName();

    /** @Warning
     * 참고로 Object메소드는 default로 재정의 할 수 없다는 제약이 있다.
     * 우리가 만든 인터페이스만 default메소드를 정의할 수 있다. 다른 라이브러리에서 온 메소드는 default메소드를 정의할 수 없다.
     * 당연하겠지만..
     */
    static void printAnything(){
        System.out.println("Anything - Foo");
    }
}
