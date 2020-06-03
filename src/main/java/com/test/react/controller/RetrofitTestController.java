package com.test.react.controller;

import com.test.react.retrofit.model.TestModel;
import com.test.react.retrofit.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/api/retrofit", produces = MediaType.APPLICATION_JSON_VALUE)
public class RetrofitTestController {
    private final TestService testService;

    @GetMapping("/list/sync")
    public Mono<List<TestModel>> getTestModelListSync(){
        return testService.getTestModelListSync();
    }

    @GetMapping("/list/async")
    public Mono<List<TestModel>> getTestModelListAsync(){
        return testService.getTestModelListAsync();
    }
}
