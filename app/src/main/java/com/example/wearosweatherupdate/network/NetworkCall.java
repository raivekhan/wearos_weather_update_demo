package com.example.wearosweatherupdate.network;

import com.example.wearosweatherupdate.model.ServerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkCall implements MyApiInterface{


    @Override
    public void getWeatherUpdateOfTheCity(String city, final ResponseCallbackInterface<ServerResponse> callBack) {
        final RetrofitApiInterface retrofitApiInterface = RetrofitApiClient.getClient()
                .create(RetrofitApiInterface.class);
        Call<ServerResponse> call= retrofitApiInterface.getWeatherUpdate(city);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse=response.body();
                if(serverResponse!=null && response.code()==200){
                    callBack.onSuccess(serverResponse);
                } else {
                    callBack.onError(new Exception(response.message()));
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                callBack.onError(new Exception(t));
            }
        });
    }
}
