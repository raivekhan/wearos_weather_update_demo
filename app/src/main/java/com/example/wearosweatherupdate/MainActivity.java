package com.example.wearosweatherupdate;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.wearosweatherupdate.model.ServerResponse;
import com.example.wearosweatherupdate.network.MyApiInterface;
import com.example.wearosweatherupdate.network.NetworkCall;
import com.example.wearosweatherupdate.network.ResponseCallbackInterface;

public class MainActivity extends WearableActivity {

    private TextView temperatureTextView;
    private TextView feelsLikeText;
    private TextView humidityText;
    private ProgressBar progressBar;
    private String Tag = "Network test app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperatureTextView = findViewById(R.id.temperatureTextView);
        feelsLikeText = findViewById(R.id.feelsLikeText);
        humidityText = findViewById(R.id.humidityText);
        progressBar = findViewById(R.id.progressBar);
        // Enables Always-on
        setAmbientEnabled();
    }
    public boolean getNetworkStatus(){
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork != null) {
            return true;
        } else {
            return false;
        }
    }
    public void getWeatherUpdate(View view) {

        temperatureTextView.setVisibility(View.GONE);
        feelsLikeText.setVisibility(View.GONE);
        humidityText.setVisibility(View.GONE);
        if(!getNetworkStatus())
        {
            temperatureTextView.setVisibility(View.VISIBLE);
            temperatureTextView.setText("No network available");
            return;
        }
        EditText editText= findViewById(R.id.cityText);
        String city=editText.getText().toString();

        progressBar.setVisibility(View.VISIBLE); //network call will start. So, show progress bar

        MyApiInterface apiService = new NetworkCall();
        apiService.getWeatherUpdateOfTheCity(city,new ResponseCallbackInterface<ServerResponse>() {
            @Override
            public void onSuccess(ServerResponse serverResponse) {
                progressBar.setVisibility(View.GONE); //network call success. So hide progress bar
                temperatureTextView.setVisibility(View.VISIBLE);
                feelsLikeText.setVisibility(View.VISIBLE);
                humidityText.setVisibility(View.VISIBLE);

                Log.d(Tag,"got result 200"  );
                temperatureTextView.setText("Temperature: "+serverResponse.getMain().getTemp());
                feelsLikeText.setText("Feels like: "+serverResponse.getMain().getFeelsLike());
                humidityText.setText("Humidity: "+serverResponse.getMain().getHumidity());
            }

            @Override
            public void onError(Throwable th) {
                Log.d(Tag,"got result other than 200"  );
                progressBar.setVisibility(View.GONE); //network call failed. So hide progress bar
                temperatureTextView.setVisibility(View.VISIBLE);
                temperatureTextView.setText(th.getMessage());
                feelsLikeText.setText("");
                humidityText.setText("");
            }
        });
    }
}