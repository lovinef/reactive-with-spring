package com.test.react.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import java.util.Optional;

@Configuration
@EnableSwagger2WebFlux
public class SwaggerConfig implements WebFluxConfigurer {
    //swagger api 구성
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .genericModelSubstitutes(Optional.class, Mono.class, Flux.class);
    }

    //WebFluxConfigurer 에서 제공되는 ResourceHandler 추가메소드로, swagger-ui 라이브러리 내 webjars 에 포함된 외부리소스가 url 로 호출될 수 있도록 구성합니다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //swagger-ui.html 리소스 등록
        registry.addResourceHandler("/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/");
        //webjars 리소스 등록
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
