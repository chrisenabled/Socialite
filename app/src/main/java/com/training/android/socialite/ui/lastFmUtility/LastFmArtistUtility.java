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

    public static int ARTIST_TRACKS_PAGE_NUM = 1;
    public static int ARTIST_TRACKS_TOTAL_PAGES = 0;

    public LastFmArtistUtility(Context context) {
        super(context);
    }

    public JSONObject getArtistEvents(String mbid){

        JSONObject artistEventsJson = new JSONObject();

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

    public JSONObject getArtistTopTracks(String mbid){

        JSONObject artistTracksJson = new JSONObject();
        String method = "artist.gettoptracks";
        String artistMbid = "&mbid="+mbid;
        String pageNum = "&page="+ ARTIST_TRACKS_PAGE_NUM;
        String artistTracksUri = ROOT_URI + method + artistMbid + pageNum + ITEM_COUNT + API_KEY + FORMAT;

        if (NetworkUtility.isNetworkAvailable(mContext)){

            artistTracksJson =  _getResult(artistTracksUri);
            if(artistTracksJson.optJSONObject("toptracks") != null) {
                if(ARTIST_TRACKS_TOTAL_PAGES == 0){
                    JSONObject jsonObject1 = artistTracksJson.optJSONObject("toptracks");
                    JSONObject jsonObject2 = jsonObject1.optJSONObject("@attr");
                    ARTIST_TRACKS_TOTAL_PAGES = jsonObject2.optInt("totalPages");
                }
                if(ARTIST_TRACKS_PAGE_NUM < ARTIST_TRACKS_TOTAL_PAGES)
                    ARTIST_TRACKS_PAGE_NUM += 1;
            }

        }

        return artistTracksJson;
    }



}
