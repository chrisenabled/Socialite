package com.training.android.socialite.ui.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.training.android.socialite.R;
import com.training.android.socialite.ui.adapters.TracksRecyclerViewAdapter;

/**
 * Created by chrisenabled on 7/15/15.
 */
public class TracksViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public TextView mTextView;

    public TracksViewHolder(View itemView, final TracksRecyclerViewAdapter adapter) {
        super(itemView);

        mImageView = (ImageView) itemView.findViewById(R.id.trackImageView);
        mTextView = (TextView) itemView.findViewById(R.id.trackNameTextView);

       // mImageView.getLayoutParams().height = mImageView.getWidth();
    }
}
