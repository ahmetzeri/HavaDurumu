package com.example.ahmet.havadurumu.WebService;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.ahmet.havadurumu.data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Ahmet on 23.08.2015.
 */
public class YahooWeatherService {
    private WeatherServiceCallBack callBack;
    private String location;
    private  Exception error;
   public YahooWeatherService(WeatherServiceCallBack cagir)
   {
       this.callBack = cagir;
   }



    public void refreshWeather(final String location)
    {

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {

               final String yql = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")",location);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(yql));

                try {
                    URL url = new URL(endpoint);
                    URLConnection urlcon = url.openConnection();

                    InputStream  stream = urlcon.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line=reader.readLine())!=null)
                    {
                        result.append(line);
                    }

                    return result.toString();
                } catch (Exception e) {

                    error = e;

                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if(s==null&& error !=null)
                {
                    callBack.serviceFailure(error);
                return;
                }
                try {
                    JSONObject data = new JSONObject(s);
                    JSONObject queryResult = data.optJSONObject("query");
                    int count = queryResult.optInt("count");
                    if (count==0)
                    {
                        callBack.serviceFailure(new LocationWeatherException("hava durumu bilgisi bulunamadi " + location));
                        return;
                    }

                    Channel k = new Channel();
                    k.poupulate(queryResult.optJSONObject("results").optJSONObject("channel"));
                    callBack.serviceSuccess(k);
                } catch (JSONException e) {
                  callBack.serviceFailure(e);
                }

            }
        }.execute(location);

    }

    public String getLocation()
    {
        return location;
    }

    public  class LocationWeatherException extends Exception
    {
        public LocationWeatherException(String e)
        {
            super(e);
        }
    }

}
