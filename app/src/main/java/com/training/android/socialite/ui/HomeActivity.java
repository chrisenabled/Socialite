package com.training.android.socialite.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

import com.training.android.socialite.R;
import com.training.android.socialite.ui.fragments.ArtistsChartRecyclerViewFragment;
import com.training.android.socialite.ui.fragments.NavigationDrawerFragment;
import com.training.android.socialite.ui.fragments.Videos_Songs_EventsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HomeActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
    Videos_Songs_EventsFragment.OnFragmentInteractionListener, ArtistsChartRecyclerViewFragment.OnFragmentInteractionListener{

    @Bind(R.id.toolbar)
    public Toolbar mToolbar;

    SearchView mSearchView;

    public static final String SocialitePREFERENCES = "Socialite" ;
    public static final String TodayDate = "todayDateKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    public static String mCurrentCountry = "united+states";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        handleIntent(getIntent());
        ButterKnife.bind(this);
        setUpOnCreate();
        sharedpreferences = getSharedPreferences(SocialitePREFERENCES, Context.MODE_PRIVATE);
        Toast.makeText(this,getDeviceResolution(),Toast.LENGTH_LONG).show();

    }

    private void setUpOnCreate(){

        //===set toolbar as actionbar=============================
        setSupportActionBar(mToolbar);

        //===Set up the drawer=================================
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        mNavigationDrawerFragment.setToolbar(mToolbar);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, query, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        //
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        if(position == 0)
            fragmentManager.beginTransaction()
                    .replace(R.id.container, ArtistsChartRecyclerViewFragment.newInstance(this))
                    .commit();
        if(position > 0 && position <= 3)
            fragmentManager.beginTransaction()
                    .replace(R.id.container, Videos_Songs_EventsFragment.newInstance(mNavigationDrawerFragment))
                    .commit();



    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_artists_chart);
                break;
            case 2:
                mTitle = getString(R.string.title_videos);
                break;
            case 3:
                mTitle = getString(R.string.title_songs);
                break;
            case 4:
                mTitle = getString(R.string.title_events);
                break;
            case 5:
                mTitle = getString(R.string.title_favorites);
                break;
        }

        restoreActionBar();

    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setQueryHint("Search online videos/music");
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {

            getMenuInflater().inflate(R.menu.global, menu);

            restoreActionBar();

            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
            mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    private String getDeviceResolution()
    {
        int density = getResources().getDisplayMetrics().densityDpi;
        switch (density)
        {
            case DisplayMetrics.DENSITY_MEDIUM:
                return "MDPI";
            case DisplayMetrics.DENSITY_HIGH:
                return "HDPI";
            case DisplayMetrics.DENSITY_LOW:
                return "LDPI";
            case DisplayMetrics.DENSITY_XHIGH:
                return "XHDPI";
            case DisplayMetrics.DENSITY_TV:
                return "TV";
            case DisplayMetrics.DENSITY_XXHIGH:
                return "XXHDPI";
            case DisplayMetrics.DENSITY_XXXHIGH:
                return "XXXHDPI";
            default:
                return "Unknown";
        }
    }


}
