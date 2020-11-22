package com.example.wearosweatherupdate.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient {
    private static final String BASE_URL="https://api.openweathermap.org/data/2.5/";
    private static Retrofit retrofit=null;
    private static Gson gson=new GsonBuilder().setLenient().create();


    public static Retrofit getClient(){
        if(retrofit==null) {
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
