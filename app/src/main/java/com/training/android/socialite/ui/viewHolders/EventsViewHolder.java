package com.training.android.socialite.ui.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.training.android.socialite.R;
import com.training.android.socialite.ui.adapters.EventsRecyclerViewAdapter;

/**
 * Created by chrisenabled on 7/15/15.
 */
public class EventsViewHolder extends RecyclerView.ViewHolder {

    public ImageView poster;
    public TextView eventTitle;
    public TextView eventVenue;
    public TextView eventAddress;
    public TextView eventDate;
    public TextView eventUrl;

    public EventsViewHolder(View itemView, final EventsRecyclerViewAdapter adapter) {
        super(itemView);

        poster = (ImageView) itemView.findViewById(R.id.event_poster);
        eventTitle = (TextView) itemView.findViewById(R.id.eventTitleTextView);
        eventVenue = (TextView) itemView.findViewById(R.id.eventVenueTextView);
        eventAddress = (TextView) itemView.findViewById(R.id.eventAddressTextView);
        eventDate = (TextView) itemView.findViewById(R.id.eventDateTextView);
        eventUrl = (TextView) itemView.findViewById(R.id.eventUrlTextView);

        DisplayMetrics metrics = adapter.mContext.getResources().getDisplayMetrics();
        int dimension = metrics.widthPixels;
            dimension = (dimension*90)/100;

        poster.getLayoutParams().width = dimension;
        poster.getLayoutParams().height = dimension;
    }
}
