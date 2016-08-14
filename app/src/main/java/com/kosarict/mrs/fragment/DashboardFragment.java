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


public class DashboardFragment extends Fragment {
    private View layoutView;
    private FragmentActivity context;

    public DashboardFragment() {
    }

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
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
        // Inflate the layout for this fragment
        layoutView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initLinearLayout();
        return layoutView;
    }

    private void initLinearLayout() {
        LinearLayout llInsertTurnDoctor = (LinearLayout) layoutView.findViewById(R.id.llInsertTurnDoctor);
        llInsertTurnDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Hello",Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout llViewRegisteredTurns = (LinearLayout) layoutView.findViewById(R.id.llViewRegisteredTurns);
        llViewRegisteredTurns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Hello",Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout llCanceledTurns = (LinearLayout) layoutView.findViewById(R.id.llCanceledTurns);
        llCanceledTurns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Hello",Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout llGetTurnPatient = (LinearLayout) layoutView.findViewById(R.id.llGetTurnPatient);
        llGetTurnPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Hello",Toast.LENGTH_LONG).show();
            }
        });

    }


}
