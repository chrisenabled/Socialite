package com.training.android.socialite.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.training.android.socialite.R;
import com.training.android.socialite.ui.adapters.TracksRecyclerViewAdapter;
import com.training.android.socialite.ui.lastFmUtility.LastFmArtistUtility;
import com.training.android.socialite.ui.models.Track;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TracksRecyclerViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TracksRecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TracksRecyclerViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static Context mContext;
    private View rootView;
    private RecyclerView mTracksRV;
    private RecyclerView.LayoutManager mLayoutManager;
    private TracksRecyclerViewAdapter mTracksRecyclerViewAdapter;
    static List<Track> mTracks = new ArrayList<>();
    private String mArtistMbid;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TracksRecyclerViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TracksRecyclerViewFragment newInstance(Context context) {
        TracksRecyclerViewFragment fragment = new TracksRecyclerViewFragment();
        mContext = context;
        return fragment;
    }

    public TracksRecyclerViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_tracks_recycler_view, container, false);
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mTracksRV = (RecyclerView) rootView.findViewById(R.id.tracksRV);
        mTracksRV.setLayoutManager(mLayoutManager);
        mTracksRecyclerViewAdapter = new TracksRecyclerViewAdapter(mContext);
        mTracksRV.setAdapter(mTracksRecyclerViewAdapter);
        mArtistMbid = "69989475-2971-49aa-8c53-5d74af88b8be";
        new GetTracksAsync().execute();
        return rootView;
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

    public class GetTracksAsync extends AsyncTask<String, Void, ArrayList<Track>> {


        @Override
        protected ArrayList<Track> doInBackground(String... params) {

            return (ArrayList<Track>) getTracksOnline(mArtistMbid, mContext);

        }

        @Override
        protected void onPostExecute(ArrayList<Track> tracks) {

            mTracks = new ArrayList<>();
            for(Track track : tracks){
                mTracks.add(track);
                mTracksRecyclerViewAdapter.add(mTracks.size() - 1, track);
            }

        }
    }

    public List<Track> getTracksOnline(String artistMbid, Context mContext){

        JSONObject artistTracks;
        JSONObject topTracks;
        JSONArray tracksJson;
        List<Track> tracks = new ArrayList<>();


        try {
            artistTracks = new LastFmArtistUtility(mContext)
                    .getArtistTopTracks(artistMbid);
            topTracks = artistTracks.getJSONObject("toptracks");
            tracksJson = topTracks.optJSONArray("track");
            for(int i = 0; i < tracksJson.length(); i++){
                Track track;
                JSONObject trackObj = tracksJson.getJSONObject(i);
                track = new Track(trackObj);
                tracks.add(track);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return tracks;

    }

}
