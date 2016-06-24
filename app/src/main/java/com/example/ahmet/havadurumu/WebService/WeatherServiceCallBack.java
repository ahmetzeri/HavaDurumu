package com.example.ahmet.havadurumu.WebService;

import com.example.ahmet.havadurumu.data.Channel;

/**
 * Created by Ahmet on 23.08.2015.
 */
public interface WeatherServiceCallBack {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception e);
}
