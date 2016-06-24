package com.example.ahmet.havadurumu.data;

import org.json.JSONObject;

/**
 * Created by Ahmet on 23.08.2015.
 */
public class Condition implements JSONpopulator {
    private  int code;
    private  int temprature;
    private  String description;
    @Override
    public void poupulate(JSONObject data) {

        code = data.optInt("code");
        temprature = data.optInt("temp");
        description = data.optString("text");
    }

    public int getCode() {
        return code;
    }

    public int getTemprature() {
        return temprature;
    }

    public String getDescription() {
        return description;
    }
}
