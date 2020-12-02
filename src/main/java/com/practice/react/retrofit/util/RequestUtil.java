package com.practice.react.retrofit.util;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.Optional;

public abstract class RequestUtil {
    // 1. 호출할  도메인
    private static final String BASE_URL = "http://localhost:8080/";
    // 2. log interceptor
    private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    // 3. 사용할 http client
    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor);
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(httpClient.build())
            .build();

    // 4. 서비스 등록
    public static <T> T createService(Class<T> sClass) {
        return retrofit.create(sClass);
    }

    // 5. 서비스 호출
    public static <T> Optional<T> requestSync(Call<T> call) {
        try {
            Response<T> execute = call.execute();
            System.out.println("execute = " + execute);
            if (execute.isSuccessful()) {
                return Optional.ofNullable(execute.body());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static <T> void requestAsync(Call<T> call, CustomCallback<T> callback) {
        call.enqueue(callback);
    }
}
