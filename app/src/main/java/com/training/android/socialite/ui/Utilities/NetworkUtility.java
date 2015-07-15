package com.training.android.socialite.ui.Utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by chrisenabled on 7/11/15.
 */
public class NetworkUtility {



    public static boolean isNetworkAvailable(Context mContext){

        ConnectivityManager connMgr = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();

    }
}
