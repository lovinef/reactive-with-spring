package com.practice.react;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class ReactiveWithSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveWithSpringApplication.class, args);
    }

}
