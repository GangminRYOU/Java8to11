package com.example.java8to11.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Container가 가지는 Annotation의 Retention과 Target 범위는 자기 자신보다 같거나 더 넓어야 한다.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
public @interface ChickenContainer {
    //자기 자신이 감싸고 있을 Annotation을 배열로 가지고 있어야 한다
    Chicken[] value();
}
