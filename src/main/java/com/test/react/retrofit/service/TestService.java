package com.test.react.retrofit.service;

import com.test.react.retrofit.api.TestApi;
import com.test.react.retrofit.model.TestModel;
import com.test.react.retrofit.util.CustomCallback;
import com.test.react.retrofit.util.RequestUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TestService {
    public Mono<List<TestModel>> getTestModelListSync(){
        // Mono.just 사용
        return Mono.just(RequestUtil.createService(TestApi.class))
                .map(TestApi::getTestModelList)
                .map(RequestUtil::requestSync)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .subscribeOn(Schedulers.elastic());

        // Mono.create 사용
//        return Mono.create(sink -> {
//            TestApi testApi = RequestUtil.createService(TestApi.class);
//            Call<List<TestModel>> testModelList = testApi.getTestModelList();
//            Optional<List<TestModel>> testModels = RequestUtil.requestSync(testModelList);
//
//            if(testModels.isPresent()) {
//                sink.success(testModels.get());
//            }else{
//                sink.success(null);
//            }
//        });
    }

    public Mono<List<TestModel>> getTestModelListAsync(){
        return Mono.create(sink ->{
            TestApi testApi = RequestUtil.createService(TestApi.class);
            RequestUtil.requestAsync(testApi.getTestModelList(), new CustomCallback<List<TestModel>>() {
                @Override
                public void onResponse(Call<List<TestModel>> call, Response<List<TestModel>> response) {
                    if(!response.isSuccessful()){
                        sink.error(new Exception("response is empty"));
                        return;
                    }

                    sink.success(Objects.requireNonNull(response.body()));
                }
            });
        });
    }

    public Mono<List<TestModel>> getPagingTestModel(int blockCnt, int page) {
        return Mono.create(sink -> {
            TestApi testApi = RequestUtil.createService(TestApi.class);
            Call<List<TestModel>> testModelList = testApi.getPagingTestModel(blockCnt, page);
            Optional<List<TestModel>> testModels = RequestUtil.requestSync(testModelList);

            if(testModels.isPresent()) {
                sink.success(testModels.get());
            }else{
                sink.error(new Exception("has no data"));
            }
        });
    }

    public Mono<List<TestModel>> monoError(){
        return Mono.create(sink -> {
//            sink.success(new ArrayList<>());    // 에러반환 방지
            sink.error(new Exception("mono error"));    // 에러 반환
        });
    }
}
