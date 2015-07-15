package com.training.android.socialite.ui;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.training.android.socialite.R;
import com.training.android.socialite.ui.connectors.YoutubeConnector;

/**
 * Created by chrisenabled on 6/23/15.
 */
public class PlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView playerView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.activity_player);

        playerView = (YouTubePlayerView)findViewById(R.id.player_view);
        playerView.initialize(YoutubeConnector.KEY, this);
    }

    @Override
    public void onInitializationSuccess(
            YouTubePlayer.Provider provider,
            YouTubePlayer youTubePlayer, boolean b) {

        if(!b){
            youTubePlayer.cueVideo(getIntent().getStringExtra("VIDEO_ID"));
        }

    }

    @Override
    public void onInitializationFailure(
            YouTubePlayer.Provider provider, YouTubeInitializationResult
            youTubeInitializationResult) {

        Toast.makeText(this, "Failed to initialize Youtube Player", Toast.LENGTH_LONG).show();


    }
}
