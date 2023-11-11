package com.example.java8to11;

import com.example.java8to11.function.ReturnSomething;

public class FooVar {
    public static void main(String[] args) {
        // 수학적함수는 입력받은 값이 같은 경우, 결과가 같아야한다.
        ReturnSomething returnSomething = (number) -> {
            return number + 10;
        };
        System.out.println(returnSomething.doSomething(1));
        System.out.println(returnSomething.doSomething(1));
        System.out.println(returnSomething.doSomething(1));
        System.out.println(returnSomething.doSomething(1));

        //CASE1: 함수 외부의 값을 참조하는 경우, 함수형 프로그래밍이라고 볼수 없다.
        ReturnSomething result = new ReturnSomething() {
            int baseNumber = 10;

            @Override
            public int doSomething(int number) {
                return number + baseNumber;
            }
        };
        //CASE2: 외부에 있는 값을 변경하려는 경우
        ReturnSomething result2 = new ReturnSomething() {
            int baseNumber = 10;

            @Override
            public int doSomething(int number) {
                baseNumber++;
                return number + baseNumber;
            }
        };
        //익명 객체 안에서 사용되는 외부 변수는 final이라고 가정하고 사용하기 때문에, 밖에서 변경 불가능하다.
    }
}
