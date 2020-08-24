package com.mad_devs.internet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static NewsService newsService;
    public static String BASE_URL = "http://newsapi.org/";

    public static NewsService getService(){
        if (newsService == null) newsService = buildRetrofit();
        return newsService;
    }

    private static NewsService buildRetrofit() {

        Gson gson = new GsonBuilder ()
                .setLenient ()
                .create ();

        return new Retrofit.Builder ()
                .baseUrl ( BASE_URL )
                .addConverterFactory ( GsonConverterFactory.create (gson))
                .build ()
                .create (NewsService.class);
    }
}
