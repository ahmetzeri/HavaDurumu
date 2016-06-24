package com.example.ahmet.havadurumu;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ahmet.havadurumu.data.Channel;
import com.example.ahmet.havadurumu.data.Item;
import com.example.ahmet.havadurumu.WebService.YahooWeatherService;
import com.example.ahmet.havadurumu.WebService.WeatherServiceCallBack;


public class MainActivity extends ActionBarActivity implements WeatherServiceCallBack {

    private ImageView weatherIconImageView;
    private TextView temratureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;

    YahooWeatherService service;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        weatherIconImageView = (ImageView)findViewById(R.id.weatherIconImageView);
        temratureTextView = (TextView)findViewById(R.id.temratureTextView);
        conditionTextView = (TextView)findViewById(R.id.condantionTextView);
        locationTextView = (TextView)findViewById(R.id.locationTextView);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);

        dialog.setMessage("Yukleniyor....");
        dialog.show();

        service.refreshWeather("Adana, Tr");


    }


    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();


        Item item = channel.getItem();
        int kaynakID = getResources().getIdentifier("drawable/icon_" + item.getCon().getCode(), null, getPackageName());

        @SuppressWarnings("deprecation")
        Drawable weatherIconDrwable = getResources().getDrawable(kaynakID);
        weatherIconImageView.setImageDrawable(weatherIconDrwable);
        temratureTextView.setText(item.getCon().getTemprature() + "\u00B0" + channel.getUnit().getTemp());
        conditionTextView.setText(item.getCon().getDescription());
        locationTextView.setText(service.getLocation());
    }

    @Override
    public void serviceFailure(Exception e) {

        dialog.hide();
        Toast.makeText(getApplicationContext(),"internet baglantisini kontrol edin",Toast.LENGTH_LONG).show();

    }
}
