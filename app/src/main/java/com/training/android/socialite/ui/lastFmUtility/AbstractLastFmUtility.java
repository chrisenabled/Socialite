package com.training.android.socialite.ui.lastFmUtility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


/**
 * Created by chrisenabled on 7/3/15.
 */
public abstract class AbstractLastFmUtility {

    final static String API_KEY = "&api_key=5d7d73fb13a3816c527b592148ca245e";
    final static String SECRET = "2c4de82615400946ffac1a6944555a15";
    final static String ROOT_URI = "http://ws.audioscrobbler.com/2.0/?method=";
    final static String FORMAT = "&format=json";
    final static String ITEM_COUNT = "&limit=20";

    CountryMetroUtility mCountryMetroUtility;
    Context mContext;

    public AbstractLastFmUtility(Context context, CountryMetroUtility countryMetroUtility){
        mContext = context;
        mCountryMetroUtility = countryMetroUtility;
    }

    public AbstractLastFmUtility(Context context){
        mContext = context;
    }

    JSONObject _getResult(String url){

        OkHttpClient client = new OkHttpClient();
        Response response;
        JSONObject jsonObject = null;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            response = client.newCall(request).execute();
            jsonObject = new JSONObject(response.body().string());

        } catch (IOException e) {
            e.getMessage();
        } catch (JSONException e) {
            e.getMessage();
        }

        return jsonObject;
    }

}
