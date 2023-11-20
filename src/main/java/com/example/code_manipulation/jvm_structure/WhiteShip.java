package com.example.code_manipulation.jvm_structure;

public class WhiteShip {
    public void work(){
        System.out.println(App.myName);
        //Class<App> 타입의 객체가 클래스를 로딩하면 Heap영역에 만들어진다.
        Class<App> appClass = App.class;
        System.out.println(appClass.getSimpleName());
    }

    public static void main(String[] args) {
        new WhiteShip().work();
    }
}
