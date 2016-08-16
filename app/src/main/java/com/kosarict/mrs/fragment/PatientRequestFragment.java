package com.kosarict.mrs.fragment;

import android.app.Activity;
import android.app.Dialog;
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
import com.kosarict.mrs.tools.PublicMethods;
import com.kosarict.mrs.view.DatePickerDialog;
import com.kosarict.mrs.view.TimePickerDialog;
import com.kosarict.wheel.WheelView;

import java.util.Calendar;

public class PatientRequestFragment extends Fragment {
    private View layoutView;
    private FragmentActivity context;
    private static final String ARG_PARAM1 = "selectedHospitalName";
    public static String selectedHospitalName;
    private Button btnPatientDate;
    private Button btnPatientTime;


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
        btnPatientDate = (Button) layoutView.findViewById(R.id.btnPatientDate);
        btnPatientTime = (Button) layoutView.findViewById(R.id.btnPatientTime);
        Button btnPatientRequestSubmit = (Button) layoutView.findViewById(R.id.btnPatientRequestSubmit);
        Button btnPatientRequestCancel = (Button) layoutView.findViewById(R.id.btnPatientRequestCancel);

        btnPatientDate.setText(PublicMethods.getCurrentPersianDate());
        btnPatientTime.setText(PublicMethods.getCurrentTime());

        btnPatientRequestCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setView(v.getId());
            }
        });

        btnPatientDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        btnPatientTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
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

    private void showDatePicker(){
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, calendar, new DatePickerDialog.DatePickerListner() {
            @Override
            public void OnDoneButton(Dialog datedialog, String c) {
                datedialog.dismiss();
                btnPatientDate.setText(c);
            }

            @Override
            public void OnCancelButton(Dialog datedialog) {
                datedialog.dismiss();
            }
        });

        datePickerDialog.setCanceledOnTouchOutside(false);
        datePickerDialog.show();
    }

    private void showTimePicker() {
        TimePickerDialog timepickerdialog = new TimePickerDialog(context, new TimePickerDialog.DatePickerListner() {
            @Override
            public void OnDoneButton(Dialog datedialog, String hour, String minute) {
                datedialog.dismiss();
                btnPatientTime.setText(hour + ":" + minute);
            }

            @Override
            public void OnCancelButton(Dialog datedialog) {
                datedialog.dismiss();
            }
        });
        timepickerdialog.setCanceledOnTouchOutside(false);
        timepickerdialog.show();
    }
}
