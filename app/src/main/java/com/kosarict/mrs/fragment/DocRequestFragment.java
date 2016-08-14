package com.kosarict.mrs.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kosarict.mrs.R;
import com.kosarict.mrs.activity.DoctorDashboardActivity;

public class DocRequestFragment extends Fragment {
    private View layoutView;
    private FragmentActivity context;
    private static final String ARG_PARAM1 = "selectedHospitalName";
    private String selectedHospitalName;


    public DocRequestFragment() {
    }


    public static DocRequestFragment newInstance(String selectedHospitalName) {
        DocRequestFragment fragment = new DocRequestFragment();
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
        layoutView = inflater.inflate(R.layout.fragment_docrequest, container, false);

        initButton();

        return layoutView;
    }

    private void initButton(){
        Button btnDocRequestSubmit = (Button) layoutView.findViewById(R.id.btnDocRequestSubmit);
        Button btnDocRequestCancel = (Button) layoutView.findViewById(R.id.btnDocRequestCancel);

        btnDocRequestCancel.setOnClickListener(new View.OnClickListener() {
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
            case R.id.btnDocRequestCancel:
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
