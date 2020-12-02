package com.practice.react.controller;

import com.practice.react.retrofit.model.TestModel;
import com.practice.react.retrofit.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/list/paging/{blockCnt}/{page}")
    public Mono<List<TestModel>> getPagingTestModel(@PathVariable(name="blockCnt", required = true) int blockCnt,
                                                    @PathVariable(name="page", required = true) int page){
        return testService.getPagingTestModel(blockCnt, page);
    }

    @GetMapping("/mono/error")
    public Mono<List<TestModel>> monoError(){
        return testService.monoError();
    }
}
