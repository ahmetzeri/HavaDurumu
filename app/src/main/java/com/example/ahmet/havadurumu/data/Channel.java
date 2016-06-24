package com.example.ahmet.havadurumu.data;

import org.json.JSONObject;

/**
 * Created by Ahmet on 23.08.2015.
 */
public class Channel implements JSONpopulator {

    private Item item;
    private Units unit;

    @Override
    public void poupulate(JSONObject data) {

        unit = new Units();
        unit.poupulate(data.optJSONObject("units"));
        item  = new Item();
        item.poupulate(data.optJSONObject("item"));
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Units getUnit() {
        return unit;
    }

    public void setUnit(Units unit) {
        this.unit = unit;
    }
}
