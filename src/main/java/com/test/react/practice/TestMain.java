package com.test.react.practice;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.ElementType;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestMain {
    public static void main(String[] args) {
        final List<String> basket1 = Arrays.asList(new String[]{"kiwi", "orange", "lemon", "orange", "lemon", "kiwi"});
        final List<String> basket2 = Arrays.asList(new String[]{"banana", "lemon", "lemon", "kiwi"});
        final List<String> basket3 = Arrays.asList(new String[]{"strawberry", "orange", "lemon", "grape", "strawberry"});
        final List<List<String>> baskets = Arrays.asList(basket1, basket2, basket3);
        final Flux<List<String>> basketFlux = Flux.fromIterable(baskets);

//        basketFlux로부터 중복 없이 각 과일 종류를 나열하고, 각 과일이 몇 개씩 들어있는지 각 바구니마다 출력하는 코드를 작성
        Flux.fromIterable(basket1)
                .groupBy(s -> s)
                .concatMap(groupedFlux -> groupedFlux.count()
                        .map(count -> {
                            Map<String, Long> fruitCount = new LinkedHashMap<>();
                            fruitCount.put(groupedFlux.key(), count);
                            return fruitCount;
                        })
                )
                .subscribe(System.out::println);


        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Flux.fromIterable(integers)
                .reduce(Integer::sum)
                .subscribe(System.out::println);

        List<Integer> integers2 = Arrays.asList(1, 2, 3, 4, 5);
        Flux.fromIterable(integers2)
                .reduce((s, i) -> s * i)
                .subscribe(System.out::println);

    }
}
