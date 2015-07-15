package com.training.android.socialite.ui.lastFmUtility;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.training.android.socialite.ui.Utilities.NetworkUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by chrisenabled on 7/6/15.
 */
public class LastFmArtistUtility extends AbstractLastFmUtility {

    public static int ARTIST_EVENTS_PAGE_NUM = 1;
    public static int ARTIST_EVENTS_TOTAL_PAGES = 0;

    public LastFmArtistUtility(Context context) {
        super(context);
    }

    public JSONObject getArtistEvents(String mbid){

        JSONObject artistEventsJson = new JSONObject();

        //http://ws.audioscrobbler.com/2.0/?method=artist.getevents&artist=Cher&api_key=5d7d73fb13a3816c527b592148ca245e&format=json
        String method = "artist.getevents";
        String artistMbid = "&mbid="+mbid;
        String pageNum = "&page="+ ARTIST_EVENTS_PAGE_NUM;
        String artistEventsUri = ROOT_URI + method + artistMbid + pageNum + ITEM_COUNT + API_KEY + FORMAT;

        if (NetworkUtility.isNetworkAvailable(mContext)){

            artistEventsJson =  _getResult(artistEventsUri);
            if(artistEventsJson.optJSONObject("events") != null) {
                if(ARTIST_EVENTS_TOTAL_PAGES == 0){
                    JSONObject jsonObject1 = artistEventsJson.optJSONObject("events");
                    JSONObject jsonObject2 = jsonObject1.optJSONObject("@attr");
                    ARTIST_EVENTS_TOTAL_PAGES = jsonObject2.optInt("totalPages");
                }
                if(ARTIST_EVENTS_PAGE_NUM < ARTIST_EVENTS_TOTAL_PAGES)
                    ARTIST_EVENTS_PAGE_NUM += 1;
            }

        }

        return artistEventsJson;
    }



}
