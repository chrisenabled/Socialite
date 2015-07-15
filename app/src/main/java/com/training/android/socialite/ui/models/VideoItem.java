package com.training.android.socialite.ui.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


public class VideoItem extends Model{

    private String title;
    private String description;
    private String thumbnailURL;
    private String videoId;

    public VideoItem(){
        super();
    }

    public String getvideoId() {
        return videoId;
    }

    public void setvideoId(String id) {
        this.videoId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnail) {
        this.thumbnailURL = thumbnail;
    }
}
