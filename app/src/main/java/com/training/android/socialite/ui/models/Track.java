package com.training.android.socialite.ui.models;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by chrisenabled on 7/1/15.
 */
public class Track {

    public String trackName;
    public String imageUrl;

    public Track(){}

    public Track(JSONObject jsonObject){

        JSONArray imagesJson;
        JSONObject imageJson;

        trackName = jsonObject.optString("name");
        imagesJson = jsonObject.optJSONArray("image");
        imageJson = imagesJson.optJSONObject(imagesJson.length() - 2);
        imageUrl = imageJson.optString("#text");

    }
}
