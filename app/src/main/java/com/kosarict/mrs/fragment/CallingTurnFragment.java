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
import android.widget.TextView;

import com.kosarict.mrs.R;
import com.kosarict.mrs.tools.PublicMethods;

public class CallingTurnFragment extends Fragment {
    private View layoutView;
    private FragmentActivity context;
    private TextView lblCurrentTurn;
    private TextView lblInQueue;
    private TextView lblTotalTurn;


    public CallingTurnFragment() {
    }

    public static CallingTurnFragment newInstance() {
        return new CallingTurnFragment();
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
        layoutView = inflater.inflate(R.layout.fragment_calling_turn, container, false);

        initTextView();
        initButton();

        return layoutView;
    }

    private void initTextView(){
        TextView lblCallingTurnCurrentDate = (TextView) layoutView.findViewById(R.id.lblCallingTurnCurrentDate);
        lblCurrentTurn = (TextView) layoutView.findViewById(R.id.lblCurrentTurn);
        lblInQueue = (TextView) layoutView.findViewById(R.id.lblInQueue);
        lblTotalTurn = (TextView) layoutView.findViewById(R.id.lblTotalTurn);

        lblCallingTurnCurrentDate.setText(PublicMethods.getCurrentPersianDate());
        lblCurrentTurn.setText(PublicMethods.number2farsi(0));
        lblInQueue.setText(PublicMethods.number2farsi(34));
        lblTotalTurn.setText(PublicMethods.number2farsi(34));
    }

    private void initButton(){
        Button btnPreviousTurn = (Button) layoutView.findViewById(R.id.btnPreviousTurn);
        Button btnRetryCallingTurn = (Button) layoutView.findViewById(R.id.btnRetryCallingTurn);
        Button btnNextTurn = (Button) layoutView.findViewById(R.id.btnNextTurn);

        btnPreviousTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousTurn = Integer.parseInt(lblCurrentTurn.getText().toString()) - 1;

                if(previousTurn < 0){

                }else {
                    int countOfQueue = Integer.parseInt(lblInQueue.getText().toString()) + 1;

                    lblCurrentTurn.setText(PublicMethods.number2farsi(previousTurn));
                    lblInQueue.setText(PublicMethods.number2farsi(countOfQueue));
                }
            }
        });

        btnNextTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextTurn = Integer.parseInt(lblCurrentTurn.getText().toString()) + 1;
                int totalTurn = Integer.parseInt(lblTotalTurn.getText().toString());

                if(nextTurn > totalTurn){

                }else {
                    int countOfQueue = Integer.parseInt(lblInQueue.getText().toString()) - 1;

                    lblCurrentTurn.setText(PublicMethods.number2farsi(nextTurn));
                    lblInQueue.setText(PublicMethods.number2farsi(countOfQueue));
                }
            }
        });

        btnRetryCallingTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
