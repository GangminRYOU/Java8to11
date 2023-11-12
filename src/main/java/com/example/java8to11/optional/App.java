package com.example.java8to11.optional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import com.example.java8to11.stream_application.OnlineClass;

public class App {
    public static void main(String[] args) {
        List<com.example.java8to11.stream_application.OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new com.example.java8to11.stream_application.OnlineClass(1, "spring boot", true));
        springClasses.add(new com.example.java8to11.stream_application.OnlineClass(2, "spring data jpa", true));
        springClasses.add(new com.example.java8to11.stream_application.OnlineClass(3, "spring mvc", false));
        springClasses.add(new com.example.java8to11.stream_application.OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "spring api development", false));

        com.example.java8to11.optional.OnlineClass springBoot = new com.example.java8to11.optional.OnlineClass(1,
            "spring boot", true);
        //NPE가 뜬다.
        Duration studyDuration = springBoot.getProgress().get().getStudyDuration();
        System.out.println(studyDuration);
        System.out.println();

        //기존에 우리가 NPE를 해결했던 방식
        Progress progress = springBoot.getProgress().get();
        if(progress != null){
            System.out.println(progress.getStudyDuration());
        }
        /*NULL 체킹을 깜빡할 수 있기 떄문에 에러가 잘 발생하는 코드이다.
        그리고 NULL을 return하는 것 자체가 문제이다.
        Java 8 이전에는 null이면 에러를 던졌었는데, RuntimeException이면 좀 낫지만, Checked 예외가 발생하면..
        예외를 터뜨리는 것도 비즈니스 로직 중에 있다면, Call Stack을 사용한다는 점에서 리소스를 잡아먹는다.*/

        //1. Optional은 따라서 반드시 함수의 return값에만 써야한다.

        //2. Primitive Type Optional은 따로 있다.
        OptionalInt.of(11);
        //3. return값에 NULL을 return해도 안된다.
        Optional.empty();
        //대신 empty를 사용

        //4. Collection, Map, Stream Array, Optional은 Optional로 감싸지 말자-> 이미 비어있는 걸 표현할 수 있는데 굳이?
    }
}
