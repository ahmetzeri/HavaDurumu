package com.example.ahmet.havadurumu.data;

import org.json.JSONObject;

/**
 * Created by Ahmet on 23.08.2015.
 */
public class Item implements JSONpopulator {
    private Condition con;
    @Override
    public void poupulate(JSONObject data) {

        con = new Condition();
        con.poupulate(data.optJSONObject("condition"));
    }

    public Condition getCon() {
        return con;
    }
}
