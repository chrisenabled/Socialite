package com.training.android.socialite.ui.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.training.android.socialite.R;
import com.training.android.socialite.ui.HomeActivity;
import com.training.android.socialite.ui.adapters.MainActivityPagerAdapter;

import io.karim.MaterialTabs;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Videos_Songs_EventsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Videos_Songs_EventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Videos_Songs_EventsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    //@Bind(R.id.view_pager)
    ViewPager mViewPager;

   // @Bind(R.id.material_tabs)
    MaterialTabs mMaterialTabs;

    MainActivityPagerAdapter mAdapter;
    SearchView mSearchView;

    View mVidMusicLayout;


    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment Videos_Songs_EventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Videos_Songs_EventsFragment newInstance() {
        Videos_Songs_EventsFragment fragment = new Videos_Songs_EventsFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Videos_Songs_EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           // mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mVidMusicLayout = inflater.inflate(R.layout.fragment_videos_and_music, container, false);
        //ButterKnife.bind(getActivity());
        setUpOnCreate();

        return mVidMusicLayout;
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

    private void setUpOnCreate(){

        //===configure material_tabs tabs and view pager===========================
        mViewPager = (ViewPager) mVidMusicLayout.findViewById(R.id.view_pager);
        mMaterialTabs = (MaterialTabs) mVidMusicLayout.findViewById(R.id.material_tabs);
        mAdapter = new MainActivityPagerAdapter(this, getActivity());
        mViewPager.setAdapter(mAdapter);
        mMaterialTabs.setViewPager(mViewPager);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        mViewPager.setPageMargin(pageMargin);
        mViewPager.setCurrentItem(0);

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
