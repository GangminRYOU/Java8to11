package com.example.java8to11.stream_application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "spring api development", false));

        System.out.println("spring으로 시작하는 수업");
        //TODO
        List<OnlineClass> lazyLoading = springClasses.stream()
            .filter(onlineClass -> onlineClass.getTitle().startsWith("spring"))
            .collect(Collectors.toList());
        lazyLoading.forEach(System.out::println);
        System.out.println("close되지 않은 수업");
        //TODO
        //Java 11이전에는 Predicate.not()이 지원되지 않아서 사용불가
        List<OnlineClass> notClosedLecture = springClasses.stream()
            .filter(onlineClass -> !onlineClass.isClosed())
            .collect(Collectors.toList());
        notClosedLecture.forEach(System.out::println);
        System.out.println("수업 이름만 모아서 스트림 만들기");
        //TODO
        springClasses
            .stream()
            .map(OnlineClass::getTitle)
            .forEach(System.out::println);

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11",  false));

        List<List<OnlineClass>> keesunEvents = new ArrayList<>();
        keesunEvents.add(springClasses);
        keesunEvents.add(javaClasses);

        //FlatMap -> 내부의 list를 각각의 online 클래스 단위의 stream으로 flatten 시켜 줄 수 있다.
        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        //TODO
        keesunEvents.stream().flatMap(Collection::stream)
            //그 다음 중개형 operator에서는 List가 아닌 Online 클래스가 stream에 지나간다.
                .forEach(oc -> System.out.println(oc.getId()));
        //operator에 오는 값이 뭔지 충분히 예측이 가능해야한다.
        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        //TODO
        //중개형 operator iterate 사용
        Stream.iterate(10 ,i -> i + 1)
            .skip(10)
            .limit(10)
            .forEach(System.out::println);
        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        //TODO
        boolean test = javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println(test);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        //TODO
        List<String> collect = springClasses.stream()
            .filter(oc -> oc.getTitle().contains("spring"))
            .map(OnlineClass::getTitle)
            .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}
