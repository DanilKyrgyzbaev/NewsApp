package com.mad_devs.internet;

import com.mad_devs.data.model_Bitcoin.Example;
import com.mad_devs.data.model_top_News.TopNews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    @GET("v2/everything")
    Call<Example> getExample(
            @Query ("apiKey") String apiKey,
            @Query ("q") String q,
            @Query ("from") String from,
            @Query ( "sortBy" ) String sortBy

    );

    @GET("v2/everything")
    Call<Example> getNewsSearch(
            @Query("q") String keyword,
            @Query("apiKey") String apiKey

    );

    @GET("v2/top-headlines")
    Call<TopNews> getTopNews(
            @Query ( "apiKey" ) String apiKey,
            @Query ( "country" ) String country,
            @Query ( "category" ) String category
    );
}