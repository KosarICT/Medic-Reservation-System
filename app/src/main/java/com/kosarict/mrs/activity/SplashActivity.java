package com.kosarict.mrs.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.kosarict.mrs.R;
import com.kosarict.mrs.model.Constant;
import com.kosarict.mrs.tools.PublicMethods;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initTextView();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PublicMethods.navToPage(SplashActivity.this, DoctorDashboardActivity.class);
                finish();
            }
        }, Constant.SPLASH_TIME_OUT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initTextView(){
        TextView lblAppEnglishName = (TextView) findViewById(R.id.lblAppEnglishName);
        lblAppEnglishName.setText(Html.fromHtml(Constant.ENGLISH_APP_NAME));
    }
}
