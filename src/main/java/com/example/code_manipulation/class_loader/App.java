package com.example.code_manipulation.class_loader;

public class App {
    public static void main(String[] args) {
        ClassLoader classLoader = App.class.getClassLoader();
        // 클래스 로더 가져오기 -> ApplicationClassLoader
        System.out.println(classLoader);
        //클래스 로더는 계층형 구조다. -> ApplicationClassLoader의 부모 -> PlatformClassLoader
        System.out.println(classLoader.getParent());
        // PlatformClassLoader의 부모는 원래 BootstrapClassLoader인데 Native코드로 구현이 되어있어서 볼수가 없다.
        System.out.println(classLoader.getParent().getParent());
        // 우리가 작성하는 코드는 스프링 포함 대부분 ApplicationClassLoader에 의해서 읽어진다.

    }

    /**
     * @Concept : Load
     * ClassLoader는 먼저 제일 부모인 Bootstrap Loader에게 클래스를 읽어달라고 부탁한다.
     * 만약 읽지 못한다면, PlatformClassLoader에게 부탁하고, 모든 부모가 읽지 못한다면, 본인이 읽어오게 된다.
     * 만약, 본인도 못읽는다면, ClassNotFoundException이 발생한다.
     *
     * @Concept : Link
     * verify, prepare, resolve로 구성 되어있다.
     * prepare: static 값의 메모리를 할당한다. -> static 변수나 static 블럭의 메모리가 할당된다.
     *
     * @Important
     * 가장 중요한 개념은 바이트 코드가 가장 중요하다. 결국 컴파일을 통해 바이트 코드를 만들고 나서야 JVM이
     * 위의 개념을 토대로 프로그램을 실행한다.
     */
}
