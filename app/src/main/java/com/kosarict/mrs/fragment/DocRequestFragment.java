package com.kosarict.mrs.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kosarict.mrs.R;

public class DocRequestFragment extends Fragment {
    private View layoutView;
    private FragmentActivity context;


    public DocRequestFragment() {
    }


    public static DocRequestFragment newInstance() {
        return new DocRequestFragment();
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
        layoutView = inflater.inflate(R.layout.fragment_docrequest, container, false);
        return layoutView;
    }
}
