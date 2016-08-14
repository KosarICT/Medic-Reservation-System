package com.kosarict.mrs.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kosarict.mrs.R;
import com.kosarict.mrs.activity.DoctorDashboardActivity;
import com.kosarict.mrs.activity.MainActivity;
import com.kosarict.mrs.model.Constant;

public class ListFragment extends Fragment {
    private View layoutView;
    private FragmentActivity context;

    public ListFragment() {
    }

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        context = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment_list, container, false);

        initTextView();
        initLinearLayout();

        return layoutView;
    }

    private void initTextView() {
        TextView lblListFragmentHelp = (TextView) layoutView.findViewById(R.id.lblListFragmentHelp);
        lblListFragmentHelp.setText(Html.fromHtml(Constant.HELP));
    }

    private void initLinearLayout() {
        LinearLayout llSubmitRequestForHospital = (LinearLayout) layoutView.findViewById(R.id.llSubmitRequestForHospital);
        LinearLayout llMyHospital = (LinearLayout) layoutView.findViewById(R.id.llMyHospital);

        llSubmitRequestForHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView(v.getId());
            }
        });

        llMyHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView(v.getId());
            }
        });
    }

    private void setView(int id) {
        Fragment fragment = context.getSupportFragmentManager().findFragmentById(R.id.container);
        context.getSupportFragmentManager().beginTransaction().remove(fragment).commit();

        switch (id) {
            case R.id.llSubmitRequestForHospital:
                DoctorDashboardActivity.lblPageTitle.setText(R.string.register_fragment_title);
                fragment = RegisterFragment.newInstance();
                break;
            case R.id.llMyHospital:
                DoctorDashboardActivity.lblPageTitle.setText(R.string.title_activity_doctor_dashboard);
                fragment = MyHospitalFragment.newInstance();
        }

        if (fragment != null) {
            context.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment, "ListFragment")
                    .addToBackStack("ListFragment").commit();
        }
    }
}
