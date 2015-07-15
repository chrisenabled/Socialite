package com.training.android.socialite.ui.lastFmUtility;

import android.content.Context;

import com.training.android.socialite.ui.Utilities.NetworkUtility;

import org.json.JSONObject;


/**
 * Created by chrisenabled on 7/3/15.
 */
public class LastFmGeoUtility extends AbstractLastFmUtility {

    public static int METRO_ARTISTS_CHART_PAGE_NUM = 1;
    public static int METRO_ARTISTS_CHART_TOTAL_PAGES = 0;

    public LastFmGeoUtility(Context context, CountryMetroUtility countryMetroUtility) {
        super(context, countryMetroUtility);
    }

    public JSONObject getMetroArtistsChart(){

        JSONObject metroArtistsChartJson = new JSONObject();

        String method = "geo.getmetroartistchart";
        String country = "&country="+ mCountryMetroUtility.mCountry;
        String metro = "&metro="+ mCountryMetroUtility.mMetro;
        String pageNum = "&page="+METRO_ARTISTS_CHART_PAGE_NUM;
        String metroArtistChartUri = ROOT_URI + method + country + pageNum +
                metro + ITEM_COUNT + API_KEY + FORMAT;

        if (NetworkUtility.isNetworkAvailable(mContext)){

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
}
