package com.vucko.newsdemoapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vucko.newsdemoapp.api.models.NewsResponseModel;
import com.vucko.newsdemoapp.utils.Constants;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {
    private static ApiHelper instance;
    private Retrofit retrofit;

    private ApiHelper() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.TIMEOUT, TimeUnit.SECONDS);

        // Add custom interceptor to add API KEY to all requests
        // Rather than do it manually
        builder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter(Constants.API_KEY_NAME, Constants.API_KEY).build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        });


        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
        Gson gson = gsonBuilder.create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public synchronized static ApiHelper getInstance() {
        if (instance == null) {
            instance = new ApiHelper();
        }
        return instance;
    }

    public void getAllHeadlines(String countryCode, OnDataCallback<NewsResponseModel> onDataCallback){
        NewsService service = retrofit.create(NewsService.class);
        Call<NewsResponseModel> call = service.getTopHeadlines(countryCode);
        processCall(call, onDataCallback);
    }

    private <T> void processCall(Call<T> call, final OnDataCallback<T> onDataCallback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    onDataCallback.onSuccess(response.body());
                } else {
                    onDataCallback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                onDataCallback.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
