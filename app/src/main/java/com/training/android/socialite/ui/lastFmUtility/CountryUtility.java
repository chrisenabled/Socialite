package com.training.android.socialite.ui.lastFmUtility;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by chrisenabled on 7/4/15.
 */
public class CountryUtility  {

    public String mCountry = "united+states";
    public String mMetro = "atlanta";
    private Context mContext;
    private Location mLocation;
    public CountryUtility(Context context, Location location) {
        mLocation = location;
        mContext = context;
        resetCountryDetails();
    }


    private void resetCountryDetails(){
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        if(mLocation != null){
            try {
                List<Address> addresses =
                        geocoder.getFromLocation(mLocation.getLatitude(), mLocation.getLongitude(), 1);
                if(addresses.size() > 0){
                    Address obj = addresses.get(0);
                    mCountry = obj.getCountryName().replace(' ', '+');

                    if(obj.getSubAdminArea() == null){
                        if(obj.getPostalCode() != null){
                            mMetro = getAlternateMetro(obj.getPostalCode(), obj.getCountryCode());
                        }
                    }
                    else
                        mMetro = obj.getSubAdminArea().replace(' ','+');
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getAlternateMetro(String postalCode, String countryCode){

        String zipToMetroUri = "http://zip.getziptastic.com/v2/" + countryCode +"/" + postalCode;
        OkHttpClient client = new OkHttpClient();
        Response response = null;
        JSONObject jsonObject = null;

        Request request = new Request.Builder()
                .url(zipToMetroUri)
                .build();

        try {
            response = client.newCall(request).execute();
            jsonObject = new JSONObject(response.body().string());
            return jsonObject.optString("city").replace(' ','+');
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    public String getCountryName(String countryShort){

        switch (countryShort) {

            case "AT":
                mCountry = "Austria";
                break;
            case "AU":
                mCountry = "Australia";
                break;
            case "AW":
                mCountry = "Aruba";
                break;
            case "AZ":
                mCountry = "Azerbaijan";
                break;
            case "BY":
                mCountry = "Belarus";
                break;
            case "BE":
                mCountry = "Belgium";
                break;
            case "BR":
                mCountry = "Brazil";
                break;
            case "CA":
                mCountry = "Canada";
                break;
            case "CL":
                mCountry = "Chile";
                break;
            case "CN":
                mCountry = "China";
                break;
            case "CO":
                mCountry = "Colombia";
                break;
            case "DK":
                mCountry = "Denmark";
                break;
            case "FI":
                mCountry = "Finland";
                break;
            case "FR":
                mCountry = "France";
                break;
            case "DE":
                mCountry = "Germany";
                break;
            case "HK":
                mCountry = "Hong+Kong";
                break;
            case "IE":
                mCountry = "Ireland";
                break;
            case "IT":
                mCountry = "Italy";
                break;
            case "JP":
                mCountry = "Japan";
                break;
            case "MX":
                mCountry = "Mexico";
                break;
            case "NL":
                mCountry = "Netherlands";
                break;
            case "NZ":
                mCountry = "New+Zealand";
                break;
            case "NO":
                mCountry = "Norway";
                break;
            case "PL":
                mCountry = "Poland";
                break;
            case "PT":
                mCountry = "Portugal";
                break;
            case "RU":
                mCountry = "Russian+Federation";
                break;
            case "ES":
                mCountry = "Spain";
                break;
            case "SE":
                mCountry = "Sweden";
                break;
            case "CH":
                mCountry = "Switzerland";
                break;
            case "TW":
                mCountry = "Taiwan";
                break;
            case "TR":
                mCountry = "Turkey";
                break;
            case "UA":
                mCountry = "Ukraine";
                break;
            case "GB":
                mCountry = "United+Kingdom";
                break;
            case "US":
                mCountry = "United+States";
                break;

        }

        return mCountry;
    }
}
