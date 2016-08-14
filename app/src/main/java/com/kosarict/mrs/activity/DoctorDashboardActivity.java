package com.kosarict.mrs.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kosarict.mrs.R;
import com.kosarict.mrs.fragment.DashboardFragment;
import com.kosarict.mrs.fragment.DocRequestFragment;
import com.kosarict.mrs.fragment.EmptyFragment;
import com.kosarict.mrs.fragment.ListFragment;
import com.kosarict.mrs.fragment.MyHospitalFragment;
import com.kosarict.mrs.fragment.RegisterFragment;
import com.kosarict.mrs.model.Constant;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DoctorDashboardActivity extends AppCompatActivity {
    private TextView lblAppFarsiName;
    public static TextView lblPageTitle;
    private View pointerList;
    private View pointerSearch;
    private View pointerFolder;
    private View pointerShare;
    private View pointerWorld;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initTextView();
        initPointerView();
        setView(Constant.LIST_COMMAND, true);

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

    private void initTextView() {
        lblAppFarsiName = (TextView) findViewById(R.id.lblAppFarsiName);
        lblPageTitle = (TextView) findViewById(R.id.lblPageTitle);

        lblAppFarsiName.setText(Html.fromHtml(Constant.PERSIAN_APP_NAME));
    }

    private void initPointerView() {
        pointerList = (View) findViewById(R.id.pointerList);
        pointerSearch = (View) findViewById(R.id.pointerSearch);
        pointerFolder = (View) findViewById(R.id.pointerFolder);
        pointerShare = (View) findViewById(R.id.pointerShare);
        pointerWorld = (View) findViewById(R.id.pointerWorld);
    }

    public void btnDoctorDashboardNavigationClick(View view) {
        switch (view.getId()) {
            case R.id.rlList:
                pointerList.setVisibility(View.VISIBLE);
                pointerSearch.setVisibility(View.INVISIBLE);
                pointerFolder.setVisibility(View.INVISIBLE);
                pointerShare.setVisibility(View.INVISIBLE);
                pointerWorld.setVisibility(View.INVISIBLE);
                setView(Constant.LIST_COMMAND, false);
                break;
            case R.id.rlSearch:
                pointerList.setVisibility(View.INVISIBLE);
                pointerSearch.setVisibility(View.VISIBLE);
                pointerFolder.setVisibility(View.INVISIBLE);
                pointerShare.setVisibility(View.INVISIBLE);
                pointerWorld.setVisibility(View.INVISIBLE);
                setView(Constant.SEARCH_COMMAND, false);
                break;
            case R.id.rlFolder:
                pointerList.setVisibility(View.INVISIBLE);
                pointerSearch.setVisibility(View.INVISIBLE);
                pointerFolder.setVisibility(View.VISIBLE);
                pointerShare.setVisibility(View.INVISIBLE);
                pointerWorld.setVisibility(View.INVISIBLE);
                setView(Constant.FOLDER_COMMAND, false);
                break;
            case R.id.rlShare:
                pointerList.setVisibility(View.INVISIBLE);
                pointerSearch.setVisibility(View.INVISIBLE);
                pointerFolder.setVisibility(View.INVISIBLE);
                pointerShare.setVisibility(View.VISIBLE);
                pointerWorld.setVisibility(View.INVISIBLE);
                setView(Constant.SHARE_COMMAND, false);
                break;
            case R.id.rlWorld:
                pointerList.setVisibility(View.INVISIBLE);
                pointerSearch.setVisibility(View.INVISIBLE);
                pointerFolder.setVisibility(View.INVISIBLE);
                pointerShare.setVisibility(View.INVISIBLE);
                pointerWorld.setVisibility(View.VISIBLE);
                setView(Constant.WORLD_COMMAND, false);
                break;
        }
    }

    private void setView(String command, boolean isFirstView) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);

        if (!isFirstView)
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();

        switch (command) {
            case Constant.LIST_COMMAND:
                lblPageTitle.setText(R.string.title_activity_doctor_dashboard);
                fragment = ListFragment.newInstance();
                break;
            case Constant.SEARCH_COMMAND:
                lblPageTitle.setText(R.string.empty_fragment);
                fragment = RegisterFragment.newInstance();
                break;
            case Constant.FOLDER_COMMAND:
                lblPageTitle.setText(R.string.empty_fragment);
                fragment = EmptyFragment.newInstance();
                break;
            case Constant.SHARE_COMMAND:
                lblPageTitle.setText(R.string.empty_fragment);
                fragment = EmptyFragment.newInstance();
                break;
            case Constant.WORLD_COMMAND:
                lblPageTitle.setText(R.string.empty_fragment);
                fragment = EmptyFragment.newInstance();
                break;
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment, "ListFragment")
                    .addToBackStack("ListFragment").commit();
        }
    }


    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);

        if (fragment != null && fragment instanceof ListFragment || fragment instanceof EmptyFragment) {
            if (Constant.BACK_PRESSED + 2000 > System.currentTimeMillis()) {
                finish();
            } else
                Toast.makeText(getBaseContext(), "برای خروج کلید بازگشت را مجددا فشار دهید", Toast.LENGTH_SHORT).show();
            Constant.BACK_PRESSED = System.currentTimeMillis();
        } else if (fragment != null && fragment instanceof RegisterFragment) {
            setView(Constant.LIST_COMMAND, false);
        } else if(fragment != null && fragment instanceof MyHospitalFragment){
            setView(Constant.LIST_COMMAND, false);
        } else {
            super.onBackPressed();
        }

    }
}
