package com.test.react.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebConfig implements WebFluxConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")               // 모든 요청에 대해 CORS를 적용한다.
                .allowedOrigins("http://localhost:8085");   // 허용 URL 설정
    }
}
