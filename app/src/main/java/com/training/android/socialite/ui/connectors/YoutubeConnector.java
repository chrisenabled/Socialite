package com.training.android.socialite.ui.connectors;

import android.content.Context;
import android.util.Log;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.training.android.socialite.R;
import com.training.android.socialite.ui.models.VideoItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrisenabled on 6/23/15.
 */
public class YoutubeConnector {

    private YouTube youtube;
    private YouTube.Search.List query;
    final long MAX_QUERY = 20;
    public static String token = null;


    // Your developer key goes here
    public static final String KEY
            = "AIzaSyCRMu1XIdi4x0ciDjquHWbLC0kgsvaFiCA";

    public YoutubeConnector(Context context) {
        youtube = new YouTube.Builder(new NetHttpTransport(),
                new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest hr) throws IOException {}
        }).setApplicationName(context.getString(R.string.app_name)).build();

        try{
            query = youtube.search().list("id,snippet");
            query.setKey(KEY);
            query.setType("video");
            query.setFields("items(id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url),nextPageToken");
            query.setMaxResults(MAX_QUERY);
            if(token != null){
                query.setPageToken(token);
            }
        }catch(IOException e){
            Log.d("YC", "Could not initialize: " + e);
        }



    }

    public List<VideoItem> search(String keywords){
        query.setQ(keywords);
        try{
            SearchListResponse response = query.execute();
            List<SearchResult> results = response.getItems();
            if(response.getNextPageToken() !=  null && token != null &&
                    token.equals(response.getNextPageToken())){
                return null;
            }

            token =  response.getNextPageToken();
            List<VideoItem> items = new ArrayList<VideoItem>();
            for(SearchResult result:results){
                VideoItem item = new VideoItem();
                item.setTitle(result.getSnippet().getTitle());
                item.setDescription(result.getSnippet().getDescription());
                item.setThumbnailURL(result.getSnippet().getThumbnails().getDefault().getUrl());
                item.setvideoId(result.getId().getVideoId());
                items.add(item);
            }
            return items;
        }catch(IOException e){
            Log.d("YC", "Could not search: "+e);
            return null;
        }

    }
}
