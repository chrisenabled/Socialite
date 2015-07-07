package com.training.android.socialite.ui.lastFmUtility;

import android.content.Context;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.training.android.socialite.ui.models.Artist;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


/**
 * Created by chrisenabled on 7/3/15.
 */
public class LastFmGeoUtility extends AbstractLastFmUtility {

    public static int METRO_ARTISTS_CHART_PAGE_NUM = 1;
    public static int METRO_ARTISTS_CHART_TOTAL_PAGES = 0;

    public LastFmGeoUtility(Context context, CountryUtility countryUtility) {
        super(context, countryUtility);
    }

    public JSONObject getMetroArtistsChart(){

        JSONObject metroArtistsChartJson = new JSONObject();

        String method = "geo.getmetroartistchart";
        String country = "&country="+mCountryUtility.mCountry;
        String metro = "&metro="+mCountryUtility.mMetro;
        String pageNum = "&page="+METRO_ARTISTS_CHART_PAGE_NUM;
        String metroArtistChartUri = ROOT_URI + method + country + pageNum +
                metro + ITEM_COUNT + API_KEY + FORMAT;

        if (isNetworkAvailable()){

            metroArtistsChartJson =  _getResult(metroArtistChartUri);
            if(metroArtistsChartJson.optJSONObject("topartists") != null) {
                if(METRO_ARTISTS_CHART_TOTAL_PAGES == 0){
                    JSONObject jsonObject1 = metroArtistsChartJson.optJSONObject("topartists");
                    JSONObject jsonObject2 = jsonObject1.optJSONObject("@attr");
                    METRO_ARTISTS_CHART_TOTAL_PAGES = jsonObject2.optInt("totalPages");
                }
                if(METRO_ARTISTS_CHART_PAGE_NUM < METRO_ARTISTS_CHART_TOTAL_PAGES)
                    METRO_ARTISTS_CHART_PAGE_NUM += 1;
            }

        }

        return metroArtistsChartJson;
    }


    private JSONObject _getResult(String url){

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
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

}
