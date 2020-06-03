package com.test.react.practice;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.lang.annotation.ElementType;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

@Slf4j
public class TestMain {
    public static void main(String[] args) throws InterruptedException {
        Mono m1 = Mono.just(1).map(x -> {
            log.info("1 sleep");
            try {
                sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return x;
        }).subscribeOn(Schedulers.parallel());

        Mono m2 = Mono.just(2).map(x -> {
            log.info("2 sleep");
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return x;
        }).subscribeOn(Schedulers.parallel());

        Mono m3 = Mono.just(3).map(x -> {
            log.info("3 sleep");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return x;
        }).subscribeOn(Schedulers.parallel());

        log.info("Mono.zip(m1, m2, m3)");

        Mono.zip(m1, m2, m3)
                .subscribe(tup -> log.info("next: " + tup));

        sleep(5000);
    }
}
