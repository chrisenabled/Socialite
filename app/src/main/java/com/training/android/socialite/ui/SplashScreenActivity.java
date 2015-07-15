package com.training.android.socialite.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.activeandroid.query.Delete;
import com.training.android.socialite.R;
import com.training.android.socialite.ui.models.Artist;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class SplashScreenActivity extends Activity {

    public static final String PREFS_NAME = "Socialite";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);

        Date today = Calendar.getInstance().getTime();

        String todayDateString = df.format(today);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String value = settings.getString("todayDateString",null);

        if(value == null || !value.equals(todayDateString)){
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("todayDateString", todayDateString);
            editor.apply();
            new Delete().from(Artist.class).where("isLiked = ?", 0).execute();
        }

        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
