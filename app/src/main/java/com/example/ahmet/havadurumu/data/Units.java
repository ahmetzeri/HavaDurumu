package com.example.ahmet.havadurumu.data;

import org.json.JSONObject;

/**
 * Created by Ahmet on 23.08.2015.
 */
public class Units implements JSONpopulator {
    private String temp;
    @Override
    public void poupulate(JSONObject data) {

        temp = data.optString("temperature");
    }

    public String getTemp() {
        return temp;
    }
}
