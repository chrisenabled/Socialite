package com.training.android.socialite.ui.fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.training.android.socialite.R;
import com.training.android.socialite.ui.HomeActivity;
import com.training.android.socialite.ui.customViews.ArtistsChartImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chrisenabled on 6/13/15.
 */
public class MyFragment extends Fragment {

    @Bind(R.id.position) TextView positon;
    @Bind(R.id.custView)
    ArtistsChartImageView aciv;
    static Context mContext;

    public static MyFragment getInstance(int position, Context context){

        MyFragment fr = new MyFragment();
        mContext = context;
        Bundle args = new Bundle();
        args.putInt("position", position);
        fr.setArguments(args);

        return fr;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();

        loadBitmap("http://userserve-ak.last.fm/serve/252/105065485.png");
        if(bundle != null){
            positon.setText("The page selected is " + bundle.getInt("position"));
        }



        return view;
    }

    private Target loadtarget;

    public void loadBitmap(String url) {

        if (loadtarget == null) loadtarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                // do something with the Bitmap
                aciv.setImage(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }


        };

        Picasso.with(mContext).load(url).into(loadtarget);
    }

}
