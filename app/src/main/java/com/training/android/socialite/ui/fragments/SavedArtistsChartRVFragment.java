package com.training.android.socialite.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.training.android.socialite.R;
import com.training.android.socialite.ui.adapters.ArtistsChartRecyclerViewAdapter;
import com.training.android.socialite.ui.models.Artist;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SavedArtistsChartRVFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SavedArtistsChartRVFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SavedArtistsChartRVFragment extends Fragment {
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

    List<Artist> artistsInDatabase = new ArrayList<>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment SavedArtistsChartRVFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SavedArtistsChartRVFragment newInstance(Context context) {
        SavedArtistsChartRVFragment fragment = new SavedArtistsChartRVFragment();
        mContext = context;
        return fragment;
    }

    public SavedArtistsChartRVFragment() {
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
        rootView =  inflater.inflate(R.layout.fragment_saved_artists_chart_rv, container, false);

        mArtistsChartRV = (RecyclerView) rootView.findViewById(R.id.savedArtistsChartRV);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mArtistsChartRV.setLayoutManager(mLayoutManager);
        mArtistsChartRecyclerViewAdapter = new ArtistsChartRecyclerViewAdapter(getActivity(),
                mArtistsChartRV, SavedArtistsChartRVFragment.class.getSimpleName());
        mArtistsChartRV.setAdapter(mArtistsChartRecyclerViewAdapter);
        empty = (TextView) rootView.findViewById(R.id.empty);
        artistsInDatabase = getAllArtists();

        if(artistsInDatabase.size() > 0){
            empty.setVisibility(View.INVISIBLE);
            int i = 0;
            for (Artist artist: artistsInDatabase){
                mArtistsChartRecyclerViewAdapter.add(i, artist);
                i++;
            }
        }
        else
            empty.setVisibility(View.VISIBLE);

        return  rootView;
    }

    public static List<Artist> getAllArtists() {
        // This is how you execute a query
        return new Select()
                .from(Artist.class)
                .where("isLiked = ?", 1)
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

}
