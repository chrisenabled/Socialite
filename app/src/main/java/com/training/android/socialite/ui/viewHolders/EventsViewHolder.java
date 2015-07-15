package com.training.android.socialite.ui.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.training.android.socialite.R;
import com.training.android.socialite.ui.adapters.EventsRecyclerViewAdapter;

/**
 * Created by chrisenabled on 7/15/15.
 */
public class EventsViewHolder extends RecyclerView.ViewHolder {

    public ImageView poster;

    public EventsViewHolder(View itemView, final EventsRecyclerViewAdapter adapter) {
        super(itemView);

        poster = (ImageView) itemView.findViewById(R.id.event_poster);

        DisplayMetrics metrics = adapter.mContext.getResources().getDisplayMetrics();
        int dimension = metrics.widthPixels;
            dimension = (dimension*90)/100;

        if(dimension > 128)
            dimension = 128;
        poster.getLayoutParams().width = dimension;
        poster.getLayoutParams().height = dimension;
    }
}
