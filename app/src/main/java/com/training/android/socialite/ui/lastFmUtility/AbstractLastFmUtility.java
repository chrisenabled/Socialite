package com.training.android.socialite.ui.lastFmUtility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.training.android.socialite.ui.HomeActivity;


/**
 * Created by chrisenabled on 7/3/15.
 */
public abstract class AbstractLastFmUtility {

    final static String API_KEY = "&api_key=5d7d73fb13a3816c527b592148ca245e";
    final static String SECRET = "2c4de82615400946ffac1a6944555a15";
    final static String ROOT_URI = "http://ws.audioscrobbler.com/2.0/?method=";
    final static String FORMAT = "&format=json";
    final static String ITEM_COUNT = "&limit=20";

    CountryUtility mCountryUtility;
    Context mContext;

    public AbstractLastFmUtility(Context context, CountryUtility countryUtility){
        mContext = context;
        mCountryUtility = countryUtility;
    }

    boolean isNetworkAvailable(){

        ConnectivityManager connMgr = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();

    }

    public AbstractLastFmUtility(Context context){
        mContext = context;
    }

}
