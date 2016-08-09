package com.kosarict.mrs.tools;

import android.app.Application;

import com.kosarict.mrs.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Sadegh-Pc on 8/4/2016.
 */
public class maya extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/iransans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
