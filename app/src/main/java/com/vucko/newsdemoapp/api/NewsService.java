package com.vucko.newsdemoapp.api;

import com.vucko.newsdemoapp.api.models.NewsResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {
    @GET("top-headlines")
    Call<NewsResponseModel> getTopHeadlines(@Query("country") String countryCode, @Query("category") String category);
}
