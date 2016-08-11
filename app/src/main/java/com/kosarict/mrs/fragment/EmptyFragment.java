package com.kosarict.mrs.fragment;

import android.app.Activity;
//import android.content.Context;
//import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.LinearLayout;

import com.kosarict.mrs.R;

//import pl.droidsonroids.gif.GifImageView;


public class EmptyFragment extends Fragment {
    private View layoutView;
    private FragmentActivity context;


    public EmptyFragment() {
    }

    public static EmptyFragment newInstance() {
        return new EmptyFragment();
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
        layoutView =  inflater.inflate(R.layout.fragment_empty, container, false);

        initGIFLayout();

        return layoutView;
    }

    private void initGIFLayout() {
//        GifImageView gib = new GifImageView(context);
//        gib.setImageResource(R.mipmap.no_record);
//
//        LinearLayout llLogo = (LinearLayout) layoutView.findViewById(R.id.llNotFound);
//        llLogo.addView(gib);
    }
}
