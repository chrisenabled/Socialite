package com.training.android.socialite.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.training.android.socialite.R;
import com.training.android.socialite.ui.fragments.SavedArtistsChartRVFragment;
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
    private List<Artist> privateArtists = new ArrayList<>();
    private boolean isSavedList;
    RecyclerView mRecyclerView;

    public ArtistsChartRecyclerViewAdapter(Context context, RecyclerView rv, String fragmentName){
        mContext = context;
        mRecyclerView = rv;
        isSavedList = fragmentName.equals(SavedArtistsChartRVFragment.class.getSimpleName());
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
        if(fav == 1)
            artist.isFromOnline = 0;
        else
            artist.isFromOnline = 1;

        artist.save();
    }

    @Override
    public void onBindViewHolder(final ArtistsChartViewHolder holder, final int position) {

        holder.mArtistsChartImageView.loadBitmap(privateArtists.get(position).mImageUrl, mContext);
        holder.mRank.setText(privateArtists.get(position).mRank);
        holder.mUrl.setText(privateArtists.get(position).mUrl);
        holder.mName.setText(privateArtists.get(position).mName);
        holder.mListeners.setText(privateArtists.get(position).mListeners);
        holder.mEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("get-artist-events");
                intent.putExtra("mbid", privateArtists.get(position).mMbid);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            }
        });

        if(isSavedList){
            holder.mFavBorderButton.setVisibility(View.INVISIBLE);
            holder.mFavButton.setVisibility(View.INVISIBLE);
        }
        else{
            holder.mFavButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setVisibility(View.INVISIBLE);
                    privateArtists.get(position).isLiked = 0;
                    holder.mFavBorderButton.setVisibility(View.VISIBLE);
                    updateArtistFav(privateArtists.get(position).getId(), 0);

                }
            });
            holder.mFavBorderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setVisibility(View.INVISIBLE);
                    privateArtists.get(position).isLiked = 1;
                    holder.mFavButton.setVisibility(View.VISIBLE);
                    updateArtistFav(privateArtists.get(position).getId(), 1);
                }
            });

            if (privateArtists.get(position).isLiked == 0){
                holder.mFavButton.setVisibility(View.INVISIBLE);
                holder.mFavBorderButton.setVisibility(View.VISIBLE);
            }
            else{
                holder.mFavButton.setVisibility(View.VISIBLE);
                holder.mFavBorderButton.setVisibility(View.INVISIBLE);
            }
        }
    }


    @Override
    public int getItemCount() {
        return privateArtists.size();
    }


    public void add(int position, Artist artist){
        privateArtists.add(position, artist);
        notifyItemInserted(position);
    }

}
