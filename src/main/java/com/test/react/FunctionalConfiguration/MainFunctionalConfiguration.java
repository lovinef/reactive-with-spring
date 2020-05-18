package com.test.react.FunctionalConfiguration;

import com.test.react.FunctionalHandler.MainFunctionalHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
@EnableWebFlux
public class MainFunctionalConfiguration {
    @Bean
    public RouterFunction<ServerResponse> main(MainFunctionalHandler handler){
        return RouterFunctions.route(GET("/func"), handler::mainHandler);
    }

    @Bean
    public RouterFunction<ServerResponse> mainHello(MainFunctionalHandler handler){
        return RouterFunctions.route(GET("/func/{name}/{age}"), handler::mainHelloHandler);
    }

    @Bean
    public RouterFunction<ServerResponse> mainPostHello(MainFunctionalHandler handler){
        return RouterFunctions.route(POST("/func/post"), handler::mainPostHelloHandler);
    }
}
