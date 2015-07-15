package com.training.android.socialite.ui.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.training.android.socialite.R;
import com.training.android.socialite.ui.adapters.ArtistsChart_fav_bookmarkPagerAdapter;

import io.karim.MaterialTabs;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ArtistsChart_fav_bookmarkFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ArtistsChart_fav_bookmarkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArtistsChart_fav_bookmarkFragment extends Fragment implements ViewPager.OnPageChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //@Bind(R.id.view_pager)
    ViewPager mViewPager;

    // @Bind(R.id.material_tabs)
    MaterialTabs mMaterialTabs;

    ArtistsChart_fav_bookmarkPagerAdapter mAdapter;
    SearchView mSearchView;

    View mArtistChartFavBookMarkLayout;

    int itemToSelect = 0;

    private static NavigationDrawerFragment mNavigationDrawerFragment;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment ArtistsChart_fav_bookmarkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArtistsChart_fav_bookmarkFragment newInstance(NavigationDrawerFragment navigationDrawerFragment) {
        ArtistsChart_fav_bookmarkFragment fragment = new ArtistsChart_fav_bookmarkFragment();
        Bundle args = new Bundle();

        mNavigationDrawerFragment = navigationDrawerFragment;
        fragment.setArguments(args);
        return fragment;
    }

    public ArtistsChart_fav_bookmarkFragment() {
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
        mArtistChartFavBookMarkLayout = inflater.inflate(R.layout.fragment_artists_chart_fav_bookmark, container, false);
        setUpOnCreate();

        return mArtistChartFavBookMarkLayout;
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
        mViewPager = (ViewPager) mArtistChartFavBookMarkLayout.findViewById(R.id.landing_page_view_pager);
        mMaterialTabs = (MaterialTabs) mArtistChartFavBookMarkLayout.findViewById(R.id.landing_page_material_tabs);
        mAdapter = new ArtistsChart_fav_bookmarkPagerAdapter(this, getActivity());
        mViewPager.setAdapter(mAdapter);
        mMaterialTabs.setViewPager(mViewPager);

        if( NavigationDrawerFragment.mCurrentSelectedPosition == 0)
            mViewPager.setCurrentItem(0);
        if( NavigationDrawerFragment.mCurrentSelectedPosition == 4)
            mViewPager.setCurrentItem(1);
        if( NavigationDrawerFragment.mCurrentSelectedPosition == 5)
            mViewPager.setCurrentItem(2);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(position == 0)
            mNavigationDrawerFragment.selectItem(position, false);
        else
            mNavigationDrawerFragment.selectItem(position + 3, false);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
