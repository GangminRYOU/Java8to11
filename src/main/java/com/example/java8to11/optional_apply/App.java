package com.example.java8to11.optional_apply;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.java8to11.optional.OnlineClass;
import com.example.java8to11.optional.Progress;

public class App {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));

        Optional<OnlineClass> spring = springClasses.stream()
            .filter(oc -> oc.getTitle().startsWith("spring"))
            .findFirst();
        boolean present = spring.isPresent();
        System.out.println(present);
        Optional<OnlineClass> kafka = springClasses.stream()
            .filter(oc -> oc.getTitle().startsWith("kafka"))
            .findFirst();
        //Java 11부터는 isEmpty()도 지원
        OnlineClass onlineClass = spring.get();
        //값이 있으면 그냥 가져올수 있다.
        //비어있다면 NoSuchElementException이 발생한다.

        //TODO: if말고 Optional API를 사용하는 방법
        spring.ifPresent(oc -> System.out.println(oc.getId()));
        // 있으면 객체를 가져오고 없으면 인스턴스를 반환해라
        OnlineClass hello = spring.orElse(createNewClass());
        OnlineClass jpa = kafka.orElse(createNewClass());
        //단점 -> 있든 없든 orElse()의 연산은 수행된다.
        // 위의 단점을 보완한 메소드: orElseGet(Supplier) -> Supplier라는 함수형 interface를 사용하기 때문에 충분히 Lazy하게 Callback될 수 있다.
        kafka.orElseGet(App::createNewClass);
        //Supplier가 호출되지 않는 경우
        spring.orElseGet(App::createNewClass);
        /**
         * orElse()의 경우에는 이미 만들어져 있는 Instance를 사용하는 경우에 더 적합
         * orElseGet()은 동적으로 작업을 하는 경우에 더 적합
         */
        //무언가를 만들지 않고 예외를 던져야하는 경우 -> orElseThrow()
        OnlineClass optional = kafka.orElseThrow(IllegalStateException::new);

        //값이 있다는 가정하에, 어떤 값을 걸러낼 수도 있다. -> filter -> 반환 결과는 Optional이다.
        Optional<OnlineClass> empty = spring.filter(oc -> oc.getId() > 20);
        //값이 없다면 -> empty인 Optional이 나온다.

        //Map도 사용할 수 있다. -> map의 반환타입에 따라 Optional<T>의 T타입이 달라진다.
        Optional<Integer> mapped = spring.map(oc -> oc.getId());
        //Map으로 꺼내는 타입이 Optional이라면?
        Optional<Optional<Progress>> redundant = spring.map(OnlineClass::getProgress);
        // -> 이런 경우에 사용할 수 있는 API가 flatMap이다.
        Optional<Progress> cut = spring.flatMap(oc -> oc.getProgress());
        //아래와 비슷하다.
        spring.map(OnlineClass::getProgress).orElse(Optional.empty());

        //<-> Stream에서의 flatMap()은 1대1 매핑이다. -> Input은 하나지만 Output은 여러개인 상황에서 사용하는 것
        //springClasses.stream().flatMap(s -> s.)
    }

    private static OnlineClass createNewClass() {
        System.out.println("createNewClss() is called!");
       return new OnlineClass(10, "JPA", false);
    }
}
