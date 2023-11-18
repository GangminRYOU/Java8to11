package com.example.java8to11.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
//Generics의 <T> -> 타입 Parameter에 사용할 수 있다.
//@Target(ElementType.TYPE_PARAMETER)
//Generics포함 모든 타입이 들어간 곳에 사용할수 있다.
@Target(ElementType.TYPE_USE)
//Container Annotation Type을 선언해주어야한다.
@Repeatable(ChickenContainer.class)
public @interface Chicken {
    String value();
}
