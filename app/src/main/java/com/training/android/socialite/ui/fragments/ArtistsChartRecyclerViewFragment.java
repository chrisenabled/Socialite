package com.training.android.socialite.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.training.android.socialite.R;
import com.training.android.socialite.ui.Utilities.NetworkUtility;
import com.training.android.socialite.ui.adapters.ArtistsChartRecyclerViewAdapter;
import com.training.android.socialite.ui.lastFmUtility.CountryMetroUtility;
import com.training.android.socialite.ui.lastFmUtility.LastFmGeoUtility;
import com.training.android.socialite.ui.models.Artist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ArtistsChartRecyclerViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ArtistsChartRecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArtistsChartRecyclerViewFragment extends Fragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    static Context mContext;
    private View rootView;
    private RecyclerView mArtistsChartRV;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArtistsChartRecyclerViewAdapter mArtistsChartRecyclerViewAdapter;
    private TextView empty;
    private ProgressBar artistsChartProgressBar;
    static List<Artist> mArtists = new ArrayList<>();

    private GoogleApiClient mGoogleApiClient;
    public static Location mLastLocation;

    public static final String PREFS_NAME = "Socialite";
    SharedPreferences settings;
    List<Artist> artistsInDatabase = new ArrayList<>();


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment ArtistsChartRecyclerViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArtistsChartRecyclerViewFragment newInstance(Context context) {
        ArtistsChartRecyclerViewFragment fragment = new ArtistsChartRecyclerViewFragment();
        mContext = context;
        return fragment;
    }

    public ArtistsChartRecyclerViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_artists_chart_recycler_view, container, false);

        settings = mContext.getSharedPreferences(PREFS_NAME, 0);
        mArtistsChartRV = (RecyclerView) rootView.findViewById(R.id.artistsChartRV);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mArtistsChartRV.setLayoutManager(mLayoutManager);
        mArtistsChartRecyclerViewAdapter = new ArtistsChartRecyclerViewAdapter(getActivity(),
                mArtistsChartRV, ArtistsChartRecyclerViewFragment.class.getSimpleName());
        mArtistsChartRV.setAdapter(mArtistsChartRecyclerViewAdapter);
        empty = (TextView) rootView.findViewById(R.id.empty);
        artistsChartProgressBar = (ProgressBar) rootView.findViewById(R.id.ArtistsChartProgressBar);
        artistsChartProgressBar.setVisibility(View.INVISIBLE);
        artistsInDatabase = getAllArtists();
        if(savedInstanceState != null){
            restoreRecyclerViewState();
        }
        //else if(!isDueTime() && artistsInDatabase.size() >= 15){
        //    reloadRecyclerView();
       // }
        else
            buildGoogleApiClient();

        return rootView;
    }

    public void restoreRecyclerViewState(){
        Toast.makeText(getActivity(), "Recreated", Toast.LENGTH_LONG).show();
        List<Artist> newArtistsList = new ArrayList<>();
        for(Artist artist : mArtists){
            newArtistsList.add(artist);
            mArtistsChartRecyclerViewAdapter.add(newArtistsList.size() - 1, artist);
        }
    }

    public void reloadRecyclerView(){

        List<Artist> artists = new Select()
                .from(Artist.class)
                .where("isFromOnline = ?", 1)
                .execute();
        empty.setVisibility(View.INVISIBLE);
        mArtists = new ArrayList<>();
        for(Artist artist : artists){
            mArtists.add(artist);
            mArtistsChartRecyclerViewAdapter.add(mArtists.size() - 1, artist);

        }
        Toast.makeText(getActivity(), "Result from Database", Toast.LENGTH_LONG).show();

    }

    public static List<Artist> getAllArtists() {
        // This is how you execute a query
        return new Select()
                .from(Artist.class)
                .where("isFromOnline = ?", 1)
                .execute();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    //=======Get Location from Google Location Service and perform task on the callbacks=========
    @Override
    public void onConnected(Bundle bundle) {

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

       if( NetworkUtility.isNetworkAvailable(mContext))
            new GetArtistsChartAsync().execute();
        else
           empty.setText("Could not connect to the internet, please check your network settings");
    }

    @Override
    public void onConnectionSuspended(int i) {


    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if(artistsInDatabase.size() > 0)
            reloadRecyclerView();
        else
            empty.setText("Could not connect to the internet, please check your network settings");

    }
    //------------------------------------------------------------------------------

    //=====Method to initiate connection with Google Location Service==========
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
    }


    //=======Class to Get Artists Chart from REST API asynchronously===================
    public class GetArtistsChartAsync extends AsyncTask<String, Void, ArrayList<Artist>> {

        CountryMetroUtility countryMetroUtility;

        @Override
        protected ArrayList<Artist> doInBackground(String... params) {

            String currentMetro = settings.getString("currentMetro", null);
            String currentCountry = settings.getString("currentCountry", null);
            SharedPreferences.Editor editor = settings.edit();
            countryMetroUtility = new CountryMetroUtility(mContext, mLastLocation);

            if(currentMetro == null || !currentMetro.equals(countryMetroUtility.mMetro) ||
                    currentCountry == null || !currentCountry.equals(countryMetroUtility.mCountry)){
                editor.putString("currentMetro", countryMetroUtility.mMetro);
                editor.putString("currentCountry", countryMetroUtility.mCountry);
                editor.apply();
                new Delete().from(Artist.class).execute();
                return (ArrayList<Artist>) getArtistsChartOnline(countryMetroUtility, mContext);
            }
            else {
                return null;

            }


        }

        @Override
        protected void onPostExecute(ArrayList<Artist> artists) {
            Toast.makeText(mContext, SystemClock.elapsedRealtime()+"",Toast.LENGTH_LONG).show();

            if(artists == null){
                if(artistsInDatabase.size() > 0)
                reloadRecyclerView();
            }

            else if(artists.isEmpty()){
                empty.setText("Could not find artists");
            }
            else {

                empty.setVisibility(View.INVISIBLE);
                mArtists = new ArrayList<>();
                for(Artist artist : artists){
                    Artist dbArtist = new Select()
                            .from(Artist.class)
                            .where("mbid = ?", artist.mMbid)
                            .executeSingle();
                    if(dbArtist != null && dbArtist.isLiked == 1)
                        artist.isLiked = 1;
                    mArtists.add(artist);
                    mArtistsChartRecyclerViewAdapter.add(mArtists.size() - 1, artist);

                    if(dbArtist == null)
                        artist.isFromOnline = 1;
                        artist.save();
                }

            }

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public List<Artist> getArtistsChartOnline(CountryMetroUtility mCountryMetroUtility, Context mContext){

        JSONObject metroArtists;
        JSONObject topArtists;
        JSONArray artists;
        LastFmGeoUtility mLastFmGeoUtility;
        List<Artist> artistsList = new ArrayList<>();

        mLastFmGeoUtility  = new LastFmGeoUtility(mContext, mCountryMetroUtility);
        try {
            metroArtists = mLastFmGeoUtility.getMetroArtistsChart();
            topArtists = metroArtists.getJSONObject("topartists");
            artists = topArtists.getJSONArray("artist");
            for(int i = 0; i < artists.length(); i++){
                Artist artist;

                JSONObject artistObj = artists.getJSONObject(i);
                artist = new Artist(artistObj);
                artistsList.add(artist);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return artistsList;

    }

    public boolean isDueTime(){

        long savedTime = settings.getLong("savedTime", 0);
        SharedPreferences.Editor editor = settings.edit();
        if(savedTime == 0){
            editor.putLong("savedTime", System.currentTimeMillis());
            editor.apply();
            return  true;
        }
        else {
            double elapsedTime = (System.currentTimeMillis() - savedTime) * 0.000000277778;
            if(elapsedTime >= 5){
                editor.putLong("savedTime", System.currentTimeMillis());
                editor.apply();
                return true;
            }
            else
                return false;

        }
    }


}
