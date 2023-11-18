package com.example.java8to11;

public class MetaSpace {
    /**
     * PermGen영역은 공간이 고정으로 할당되고, 그 안에 데이터가 쌓이는 형태
     * 근데 여기가 클래스가 마구 생성되고 하다 보면 넘쳐서 GC하다가 또 넘치면 OutOfMemory에러가 난다.
     * PermGen 사이즈를 늘리는 것은 해결 책이 아니다.
     *
     * JAVA 8에서 제공 되는 것
     * JVM Heap에 PermGen 영역이 없어지고, Native 메모리에 MetaSpace라는 이름으로 자리 잡았다.
     * MetaSpace는 고정된 사이즈가 없다.
     * 우리가 신경써야하는 상황은 MetaSpace가 지나치게 늘어나진 않는지이다. -> MetaSpace가 지속적으로 늘어나면 우리 OS를
     * 다 잡아먹는 상황도 생길 수 있다.
     * -> 우리가 해줄 수 있는 것은 MaxMetaSpaceSize=N 으로 최대 사이즈를 정해주는 것이다.
     */
}
