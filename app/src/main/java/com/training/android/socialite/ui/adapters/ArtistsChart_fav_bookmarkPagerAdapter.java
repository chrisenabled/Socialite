package com.training.android.socialite.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.training.android.socialite.R;
import com.training.android.socialite.ui.fragments.ArtistsChartRecyclerViewFragment;
import com.training.android.socialite.ui.fragments.MyFragment;
import com.training.android.socialite.ui.fragments.SavedArtistsChartRVFragment;

import io.karim.MaterialTabs;

/**
 * Created by chrisenabled on 7/11/15.
 */
public class ArtistsChart_fav_bookmarkPagerAdapter extends FragmentStatePagerAdapter implements MaterialTabs.CustomTabProvider {

    private final String[] TITLES = {"Artist Chart", "Favorites", "Bookmarks"};
    private ArtistsChartRecyclerViewFragment mArtistsChartRecyclerViewFragment;
    private SavedArtistsChartRVFragment mSavedArtistsChartRVFragment;

    private final int[] ICONS = {R.drawable.ic_person_white, R.drawable.ic_favorite_white,
            R.drawable.ic_bookmark_white};

    private Context mContext;

    public ArtistsChart_fav_bookmarkPagerAdapter(Fragment fm, Context context) {
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

        if(position == 0)
            return mArtistsChartRecyclerViewFragment = ArtistsChartRecyclerViewFragment.newInstance(mContext);
        if(position == 1)
            return mSavedArtistsChartRVFragment = SavedArtistsChartRVFragment.newInstance(mContext);

        return ArtistsChartRecyclerViewFragment.newInstance(mContext);
    }

    @Override
    public View getCustomTabView(ViewGroup parent, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageDrawable(mContext.getResources().getDrawable(ICONS[position]));
        return imageView;
    }

}
