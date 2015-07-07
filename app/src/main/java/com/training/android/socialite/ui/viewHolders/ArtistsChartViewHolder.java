package com.training.android.socialite.ui.viewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.training.android.socialite.R;
import com.training.android.socialite.ui.adapters.ArtistsChartRecyclerViewAdapter;
import com.training.android.socialite.ui.customViews.ArtistsChartImageView;

import at.markushi.ui.CircleButton;

/**
 * Created by chrisenabled on 7/6/15.
 */
public class ArtistsChartViewHolder extends RecyclerView.ViewHolder {

    public CardView mCardView;
    public ArtistsChartImageView mArtistsChartImageView;
    public TextView mName;
    public TextView mListeners;
    public TextView mUrl;
    public TextView mRank;
    public TextView mNameLabel;
    public TextView mListenersLabel;
    public TextView mUrlLabel;
    public TextView mRankLabel;
    public CircleButton mEventButton;
    public CircleButton mMusicButton;
    public CircleButton mVideoButton;
    public CircleButton mFavButton;
    public CircleButton mFavBorderButton;


    public ArtistsChartViewHolder(View itemView, final ArtistsChartRecyclerViewAdapter adapter) {

        super(itemView);

        mCardView = (CardView) itemView.findViewById(R.id.artistsChartCV);
        mArtistsChartImageView = (ArtistsChartImageView) itemView.findViewById(R.id.artistChartIV);
        mName = (TextView) itemView.findViewById(R.id.artistChartName);
        mListeners = (TextView) itemView.findViewById(R.id.artistChartListeners);
        mUrl = (TextView) itemView.findViewById(R.id.artistChartUrl);
        mRank = (TextView) itemView.findViewById(R.id.artistChartRank);
        mNameLabel = (TextView) itemView.findViewById(R.id.artistChartNameLabel);
        mListenersLabel = (TextView) itemView.findViewById(R.id.artistChartListenersLabel);
        mUrlLabel = (TextView) itemView.findViewById(R.id.artistChartUrlLabel);
        mRankLabel = (TextView) itemView.findViewById(R.id.artistChartRankLabel);
        mEventButton = (CircleButton) itemView.findViewById(R.id.artistChartEventButton);
        mMusicButton = (CircleButton) itemView.findViewById(R.id.artistChartMusicButton);
        mVideoButton = (CircleButton) itemView.findViewById(R.id.artistChartVideoButton);
        mFavButton = (CircleButton) itemView.findViewById(R.id.artistChartFavButton);
        mFavBorderButton = (CircleButton) itemView.findViewById(R.id.artistChartFavBorderButton);

        DisplayMetrics metrics = adapter.mContext.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;


        mArtistsChartImageView.getLayoutParams().width = width/4;
        mArtistsChartImageView.getLayoutParams().height = width/4;

        int textSize;

        if(width <= 1200)
            textSize = 14;
        else if(width <= 2000)
            textSize = 17;
        else if(width <= 3000)
            textSize = 20;
        else textSize = 22;

        mName.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        mNameLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        mListeners.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        mListenersLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        mUrl.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        mUrlLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        mRank.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        mRankLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);


        mEventButton.getLayoutParams().width = width/9;
        mEventButton.getLayoutParams().height = width/9;

        mMusicButton.getLayoutParams().width = width/9;
        mMusicButton.getLayoutParams().height = width/9;

        mVideoButton.getLayoutParams().width = width/9;
        mVideoButton.getLayoutParams().height = width/9;

        mFavButton.getLayoutParams().width = width/9;
        mFavBorderButton.getLayoutParams().height = width/9;

        if(width < 500){
            mEventButton.getLayoutParams().width = width/6;
            mEventButton.getLayoutParams().height = width/6;

            mMusicButton.getLayoutParams().width = width/6;
            mMusicButton.getLayoutParams().height = width/6;

            mVideoButton.getLayoutParams().width = width/6;
            mVideoButton.getLayoutParams().height = width/6;

            mFavButton.getLayoutParams().width = width/7;
            mFavBorderButton.getLayoutParams().height = width/7;
        }

        Toast.makeText(adapter.mContext, width + "", Toast.LENGTH_LONG).show();

    }
}

