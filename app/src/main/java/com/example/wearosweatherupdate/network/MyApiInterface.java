package com.example.wearosweatherupdate.network;

import com.example.wearosweatherupdate.model.ServerResponse;

public interface MyApiInterface {
    void getWeatherUpdateOfTheCity(String city, ResponseCallbackInterface<ServerResponse>callBack);
}
