package com.kosarict.mrs.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kosarict.mrs.R;
import com.kosarict.mrs.activity.DoctorDashboardActivity;

public class PatientRequestFragment extends Fragment {
    private View layoutView;
    private FragmentActivity context;
    private static final String ARG_PARAM1 = "selectedHospitalName";
    private String selectedHospitalName;


    public PatientRequestFragment() {
    }

    public static PatientRequestFragment newInstance(String selectedHospitalName) {
        PatientRequestFragment fragment = new PatientRequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, selectedHospitalName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        context = (FragmentActivity) activity;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedHospitalName = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment_pationrequest, container, false);

        initButton();

        return layoutView;
    }

    private void initButton(){
        Button btnPatientRequestSubmit = (Button) layoutView.findViewById(R.id.btnPatientRequestSubmit);
        Button btnPatientRequestCancel = (Button) layoutView.findViewById(R.id.btnPatientRequestCancel);

        btnPatientRequestCancel.setOnClickListener(new View.OnClickListener() {
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
            case R.id.btnPatientRequestCancel:
                DoctorDashboardActivity.lblPageTitle.setText(R.string.title_activity_doctor_dashboard);
                fragment = DashboardFragment.newInstance(selectedHospitalName);
                break;
        }

        if (fragment != null) {
            context.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment, "doctorDashboardActivity")
                    .addToBackStack("doctorDashboardActivity").commit();
        }
    }
}
