package com.example.java8to11.annotation;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Chicken("양념")
@Chicken("마늘 간장")
@Slf4j
public class App {
    public static void main(@Chicken("") String[] args) throws @Chicken("") RuntimeException {
        List<@Chicken("") String> names = Arrays.asList("keesun");
        //Chicken Type으로 바로 읽어오는 방법
        Chicken[] chickens = App.class.getAnnotationsByType(Chicken.class);
        Arrays.stream(chickens).forEach(c -> log.info("chicken annotation value={}", c));
        //ChickenContainer를 사용하는 방법
        ChickenContainer chickenContainer = App.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(chicken -> log.info("chickens in container={}", chicken));
    }

    static class FeelsLikeChicken<@Chicken("") T> {
        public static <@Chicken("") C> void print(@Chicken("") C c){

        }
    }
}
