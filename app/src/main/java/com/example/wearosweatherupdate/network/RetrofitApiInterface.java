package com.example.wearosweatherupdate.network;



import com.example.wearosweatherupdate.model.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitApiInterface {
    @POST("weather?appid=92756c24107bc39dd0a7541f66ba55c5&units=metric")
    Call<ServerResponse> getWeatherUpdate(@Query("q") String city);
}
