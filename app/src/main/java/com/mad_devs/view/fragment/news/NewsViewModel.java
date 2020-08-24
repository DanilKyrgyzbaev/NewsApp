package com.mad_devs.view.fragment.news;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mad_devs.data.model_Bitcoin.Example;
import com.mad_devs.data.model_top_News.TopNews;
import com.mad_devs.internet.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<Example> articleMutableLiveData = new MutableLiveData<> ();
    MutableLiveData<TopNews> topNewsMutableLiveData = new MutableLiveData<> ();
    MutableLiveData<Example> exampleMutableLiveData = new MutableLiveData<> ();
    MutableLiveData<String> error = new MutableLiveData<> ();
    public void getNewsBitkoin(String apiKey, String q){
        ApiClient.getService ().getExample ( apiKey,q,"from","sortBy").enqueue ( new Callback<Example> () {
            @Override
            public void onResponse(Call<Example> call , Response<Example> response) {
                //Error
                if (response.body () != null && response.isSuccessful ()){
                    articleMutableLiveData.postValue ( response.body () );
                    Log.e ( "news Bitkoin" , response + "" );
                } else {
                    error.postValue ( response.message () );
                    Log.e ( "--------else_error" , response + "Error" );
                }
            }

            @Override
            public void onFailure(Call<Example> call , Throwable t) {
                //Error
                error.postValue ( t.getMessage () );
                Log.e ( "--------" , t.getMessage () );
            }
        } );
    }

    public void getTopNews(String apiKey){
        ApiClient.getService ().getTopNews ( apiKey,"ru","music" ).enqueue ( new Callback<TopNews> () {
            @Override
            public void onResponse(Call<TopNews> call , Response<TopNews> response) {
                if (response.body ()!= null && response.isSuccessful ()){
                    topNewsMutableLiveData.postValue ( response.body () );
                    Log.e ( "news Top", response + "" );
                } else {
                    error.postValue ( response.message () );
                    Log.e ( "--------else_error" , response + "Error" );
                }
            }

            @Override
            public void onFailure(Call<TopNews> call , Throwable t) {
                error.postValue ( t.getMessage () );
                Log.e ( "--------" , t.getMessage ());

            }
        } );
    }

    public void getSearchNews(String apiKey, String q){
        ApiClient.getService ().getNewsSearch (q ,apiKey).enqueue ( new Callback<Example> () {
            @Override
            public void onResponse(Call<Example> call , Response<Example> response) {
                if (response.body ()!= null && response.isSuccessful ()){
                    exampleMutableLiveData.postValue ( response.body () );
                    Log.e ( "news Top", response + "" );
                } else {
                    error.postValue ( response.message () );
                    Log.e ( "--------else_error" , response + "Error" );
                }
            }

            @Override
            public void onFailure(Call<Example> call , Throwable t) {
                error.postValue ( t.getMessage () );
                Log.e ( "--------" , t.getMessage ());

            }
        } );
    }

}