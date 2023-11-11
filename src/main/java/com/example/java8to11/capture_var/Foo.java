package com.example.java8to11.capture_var;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Foo {
    public static void main(String[] args) {
        Foo foo = new Foo();
        foo.run();
    }

    private void run() {
        //우리가 사용한 변수가 캡쳐가 된다.
        // Java8이전에는 항상 final 키워드가 붙어있어야만, 익명 클래스, 내부 클래스
        // 에 사용될 수 있었따.
        int baseNumber = 10;
        // 람다에서는 그냥 참조가 가능하다.
        IntConsumer printInt = (i) -> System.out.println(i + baseNumber);
        // 하지만 다른 점이 하나 있다.
        // Shadowing이 가능하다. => 어떤 클래스를 정의하고, 내부에 변수를 정의하고, 내부에 메소드에 다시 같은 이름의 변수를 정의한다면 클래스
        // 변수는 메소드변수에 의해 가려진다.. -> 로컬 클래스와 익명 클래스 모두 이런 방식으로 동작

        // 공통점은 세가지 방법 모두, final이라는 키워드는 없지만, 사실상 final 취급한다.
        printInt.accept(10);
        // 컴파일러가 Effectie Final인지 아닌지 찾아서 에러를 보여준다.
        // baseNumber++;
        // 로컬 클래스
        class LocalClass{
            void printBaseNumber(){
                int baseNumber = 11;
                System.out.println(baseNumber);
            }
        }
        //익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                System.out.println(baseNumber);
            }
        };
    }
}
