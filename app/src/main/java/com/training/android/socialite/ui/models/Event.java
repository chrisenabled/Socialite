package com.training.android.socialite.ui.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by chrisenabled on 7/6/15.
 */

@Table(name = "events")
public class Event extends Model {

    @Column(name = "eventId")
    public String mEventId;
    @Column(name = "title")
    public String mTitle;
    @Column(name = "venueName")
    public String mVenueName;
    @Column(name = "latitude")
    public double mLatitude;
    @Column(name = "longitude")
    public double mLongitude;
    @Column(name = "address")
    public String mAddress;
    @Column(name = "url")
    public String mUrl;
    @Column(name = "startDate")
    public String mStartDate;
    @Column(name = "imageUrl")
    public String mImageUrl;


    public Event (){super();}

    public Event(JSONObject jsonObject){

        JSONObject venueJson;
        JSONObject locationJson;
        JSONObject geoPointJson;
        JSONArray imagesJson;
        JSONObject imageJson;

        venueJson = jsonObject.optJSONObject("venue");
        locationJson = venueJson.optJSONObject("location");
        geoPointJson = locationJson.optJSONObject("geo:point");
        imagesJson = jsonObject.optJSONArray("image");
        imageJson = imagesJson.optJSONObject(imagesJson.length() - 1);

        mEventId = jsonObject.optString("id");
        mTitle = jsonObject.optString("title");
        mVenueName = venueJson.optString("name");
        mLatitude = geoPointJson.optDouble("geo:lat");
        mLongitude = geoPointJson.optDouble("geo:long");

        mAddress = locationJson.optString("street") + ", " + locationJson.optString("city") + ", "
                + locationJson.optString("country") + ", " + locationJson.optString("postalcode");

        mUrl = venueJson.optString("url");
        mStartDate = jsonObject.optString("startDate");
        mImageUrl = imageJson.optString("#text");



    }























}
