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
import android.widget.TextView;

import com.kosarict.mrs.R;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment_list, container, false);

        initTextView();

        return layoutView;
    }

    private void initTextView(){
        TextView lblListFragmentHelp = (TextView) layoutView.findViewById(R.id.lblListFragmentHelp);
        lblListFragmentHelp.setText(Html.fromHtml(Constant.HELP));
    }
}
