package com.training.android.socialite.ui.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by chrisenabled on 7/3/15.
 */

@Table(name = "artists")
public class Artist extends Model{

    @Column(name = "name")
    public String mName;
    @Column(name = "listeners")
    public String mListeners;
    @Column(name = "mbid")
    public String mMbid;
    @Column(name = "url")
    public String mUrl;
    @Column(name = "rank")
    public String mRank;
    @Column(name = "imageUrl")
    public String mImageUrl;
    @Column(name="isLiked")
    public int isLiked = 0;


    public Artist(){super();}

    public Artist(JSONObject jsonObject){

        mName = jsonObject.optString("name");
        mListeners = jsonObject.optString("listeners");
        mMbid = jsonObject.optString("mbid");
        JSONArray images = jsonObject.optJSONArray("image");
        JSONObject image = images.optJSONObject(3);
        mImageUrl = image.optString("#text");
        JSONObject attr = jsonObject.optJSONObject("@attr");
        mRank = attr.optString("rank");
        mUrl = jsonObject.optString("url");
    }






}
