package com.training.android.socialite.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.training.android.socialite.R;
import com.training.android.socialite.ui.models.Track;
import com.training.android.socialite.ui.viewHolders.TracksViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrisenabled on 7/15/15.
 */
public class TracksRecyclerViewAdapter extends RecyclerView.Adapter<TracksViewHolder> {

    public Context mContext;
    private List<Track> tracks = new ArrayList<>();

    public TracksRecyclerViewAdapter(Context context){
        mContext = context;
    }


    @Override
    public TracksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tracks_lists_card_view, parent, false);
        TracksViewHolder tracksViewHolder = new TracksViewHolder(v, this);
        return tracksViewHolder;
    }

    @Override
    public void onBindViewHolder(TracksViewHolder holder, int position) {

        holder.mTextView.setText(tracks.get(position).trackName);
        Picasso.with(mContext)
                .load(tracks.get(position).imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public void add(int position, Track track){
        tracks.add(position, track);
        notifyItemInserted(position);
    }
}
