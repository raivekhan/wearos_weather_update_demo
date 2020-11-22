package com.example.wearosweatherupdate.model;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {
    @SerializedName("main")
    private Main main;


    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
