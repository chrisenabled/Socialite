package com.training.android.socialite.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.training.android.socialite.R;
import com.training.android.socialite.ui.models.Artist;
import com.training.android.socialite.ui.viewHolders.ArtistsChartViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrisenabled on 7/4/15.
 */
public class ArtistsChartRecyclerViewAdapter extends
        RecyclerView.Adapter<ArtistsChartViewHolder> {

    public Context mContext;
    public static List<Artist> mArtists = new ArrayList<>();
    private List<Artist> privateArtists = new ArrayList<>();
    RecyclerView mRecyclerView;

    public ArtistsChartRecyclerViewAdapter(Context context, RecyclerView rv){
        mContext = context;
        mRecyclerView = rv;
    }

    @Override
    public ArtistsChartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_artists_chart_card_view, parent, false);
        ArtistsChartViewHolder artistsChartViewHolder = new ArtistsChartViewHolder(v, this);
        return artistsChartViewHolder;
    }

    private void updateArtistFav(Long id, int fav){
        Artist artist = Artist.load(Artist.class, id);
        artist.isLiked = fav;
        artist.save();
    }

    @Override
    public void onBindViewHolder(final ArtistsChartViewHolder holder, final int position) {

        holder.mArtistsChartImageView.loadBitmap(privateArtists.get(position).mImageUrl, mContext);
        holder.mRank.setText(privateArtists.get(position).mRank);
        holder.mUrl.setText(privateArtists.get(position).mUrl);
        holder.mName.setText(privateArtists.get(position).mName);
        holder.mListeners.setText(privateArtists.get(position).mListeners);
        holder.mFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                mArtists.get(position).isLiked = 0;
                holder.mFavBorderButton.setVisibility(View.VISIBLE);
                updateArtistFav(mArtists.get(position).getId(), 0);

            }
        });
        holder.mFavBorderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                mArtists.get(position).isLiked = 1;
                holder.mFavButton.setVisibility(View.VISIBLE);
                updateArtistFav(mArtists.get(position).getId(), 1);
            }
        });

        holder.mEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("get-artist-events");
                intent.putExtra("mbid", mArtists.get(position).mMbid);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            }
        });


        if (mArtists.get(position).isLiked == 0){
            holder.mFavButton.setVisibility(View.INVISIBLE);
            holder.mFavBorderButton.setVisibility(View.VISIBLE);
        }
        else{
            holder.mFavButton.setVisibility(View.VISIBLE);
            holder.mFavBorderButton.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return privateArtists.size();
    }


    public void add(int position, Artist artist){
        privateArtists.add(position, artist);
        mArtists.add(position,artist);
        notifyItemInserted(position);
    }

}
