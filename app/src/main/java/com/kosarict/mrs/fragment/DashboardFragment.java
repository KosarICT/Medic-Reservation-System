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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kosarict.mrs.R;
import com.kosarict.mrs.activity.DoctorDashboardActivity;


public class DashboardFragment extends Fragment {
    private View layoutView;
    private FragmentActivity context;
    private static final String ARG_PARAM1 = "selectedHospitalName";
    private String selectedHospitalName;


    public DashboardFragment() {
    }

    public static DashboardFragment newInstance(String selectedHospitalName) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, selectedHospitalName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        context = (FragmentActivity) activity;
        super.onAttach(activity);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedHospitalName = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layoutView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        DoctorDashboardActivity.lblPageTitle.setText(selectedHospitalName);
        initLinearLayout();

        return layoutView;
    }

    private void initLinearLayout() {
        LinearLayout llInsertTurnDoctor = (LinearLayout) layoutView.findViewById(R.id.llInsertTurnDoctor);
        llInsertTurnDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView(v.getId());
            }
        });

        LinearLayout llViewRegisteredTurns = (LinearLayout) layoutView.findViewById(R.id.llViewRegisteredTurns);
        llViewRegisteredTurns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, R.string.empty_fragment, Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout llCanceledTurns = (LinearLayout) layoutView.findViewById(R.id.llCanceledTurns);
        llCanceledTurns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, R.string.empty_fragment, Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout llGetTurnPatient = (LinearLayout) layoutView.findViewById(R.id.llGetTurnPatient);
        llGetTurnPatient.setOnClickListener(new View.OnClickListener() {
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
            case R.id.llInsertTurnDoctor:
                DoctorDashboardActivity.lblPageTitle.setText(R.string.doc_request_fragment_title);
                fragment = DocRequestFragment.newInstance(selectedHospitalName);
                break;
            case R.id.llGetTurnPatient:
                DoctorDashboardActivity.lblPageTitle.setText(R.string.get_turn_patient_fragment_title);
                fragment = PatientRequestFragment.newInstance(selectedHospitalName);
                break;
        }

        if (fragment != null) {
            context.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment, "ListFragment")
                    .addToBackStack("ListFragment").commit();
        }
    }
}
