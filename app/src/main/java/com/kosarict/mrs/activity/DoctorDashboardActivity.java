package com.kosarict.mrs.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.kosarict.mrs.R;
import com.kosarict.mrs.fragment.ListFragment;
import com.kosarict.mrs.model.Constant;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DoctorDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initTextView();
        setView();

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
        TextView lblAppFarsiName = (TextView) findViewById(R.id.lblAppFarsiName);
        lblAppFarsiName.setText(Html.fromHtml(Constant.PERSIAN_APP_NAME));
    }

    private void setView(){
        Fragment fragment = ListFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, "ListFragment")
                .addToBackStack("ListFragment").commit();
    }


    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);

        if (fragment != null && fragment instanceof ListFragment) {
            if (Constant.BACK_PRESSED + 2000 > System.currentTimeMillis()) {
                finish();
            } else
                Toast.makeText(getBaseContext(), "برای خروج کلید بازگشت را مجددا فشار دهید", Toast.LENGTH_SHORT).show();
            Constant.BACK_PRESSED = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }

    }
}
