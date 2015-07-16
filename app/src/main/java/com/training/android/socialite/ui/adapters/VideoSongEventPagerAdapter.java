package com.training.android.socialite.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.training.android.socialite.R;
import com.training.android.socialite.ui.fragments.EventsRecyclerViewFragment;
import com.training.android.socialite.ui.fragments.MyFragment;
import com.training.android.socialite.ui.fragments.TracksRecyclerViewFragment;
import com.training.android.socialite.ui.fragments.VideosFragment;

import io.karim.MaterialTabs;

/**
 * Created by chrisenabled on 7/2/15.
 */
public class VideoSongEventPagerAdapter extends FragmentStatePagerAdapter implements MaterialTabs.CustomTabProvider {

    private final String[] TITLES = {"Videos", "Songs", "Events"};

    private final int[] ICONS = {R.drawable.ic_videocam_white, R.drawable.ic_queue_music_white,
                    R.drawable.ic_event_white};

    private Context mContext;

    public VideoSongEventPagerAdapter(Fragment fm, Context context) {
        super(fm.getChildFragmentManager());
        mContext = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return VideosFragment.newInstance(mContext);
        }
        if(position == 2){
            return EventsRecyclerViewFragment.newInstance(mContext);
        }
        else
            return TracksRecyclerViewFragment.newInstance(mContext);
    }

    @Override
    public View getCustomTabView(ViewGroup parent, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageDrawable(mContext.getResources().getDrawable(ICONS[position]));
        return imageView;
    }
}



