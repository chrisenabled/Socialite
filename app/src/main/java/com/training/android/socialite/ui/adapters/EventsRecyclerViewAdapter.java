package com.training.android.socialite.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.training.android.socialite.R;
import com.training.android.socialite.ui.models.Event;
import com.training.android.socialite.ui.viewHolders.EventsViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrisenabled on 7/15/15.
 */
public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventsViewHolder>  {

    public Context mContext;
    private List<Event> events = new ArrayList<>();


    public EventsRecyclerViewAdapter(Context context){
        mContext = context;
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_artists_chart_card_view, parent, false);
        EventsViewHolder eventsViewHolder = new EventsViewHolder(v, this);
        return eventsViewHolder;
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {

        Picasso.with(mContext)
                .load(events.get(position).mImageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void add(int position, Event event){
        events.add(position, event);
        notifyItemInserted(position);
    }
}
