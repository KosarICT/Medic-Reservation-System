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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kosarict.mrs.R;
import com.kosarict.mrs.model.hospitalModel;

import java.util.ArrayList;
import java.util.List;


public class MyHospitalFragment extends Fragment {
    private View layoutView;
    private FragmentActivity context;
    private List<hospitalModel> hospitalList = new ArrayList<>();


    public MyHospitalFragment() {
    }

    public static MyHospitalFragment newInstance() {
        return new MyHospitalFragment();
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
        layoutView = inflater.inflate(R.layout.fragment_my_hospital, container, false);

        initListViewDate();
        initListView();

        return layoutView;
    }

    private void initListView() {
        ListView lstMyHospital = (ListView) layoutView.findViewById(R.id.lstMyHospital);

        lstMyHospital.setAdapter(new listViewDataAdapter());
    }

    private void initListViewDate() {
        hospitalModel hospitalModel1 = new hospitalModel("بیمارستان آریا اهواز", "بیمارستان فوق تخصصی قلب");
        hospitalModel hospitalModel2 = new hospitalModel("بیمارستان خرمشهر", "بیمارستان تخصصی چشم");

        hospitalList.add(hospitalModel1);
        hospitalList.add(hospitalModel2);
    }

    private class listViewDataAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return hospitalList.size();
        }

        @Override
        public hospitalModel getItem(int position) {
            return hospitalList.get(0);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater layoutInflater = context.getLayoutInflater();
                view = layoutInflater.inflate(R.layout.lst_my_hospital_item_layout, null);
            } else {
                view = convertView;
            }

            TextView lblMyHospitalTitle = (TextView) view.findViewById(R.id.lblMyHospitalTitle);
            TextView lblMyHospitalDescription = (TextView) view.findViewById(R.id.lblMyHospitalDescription);

            lblMyHospitalTitle.setText(hospitalList.get(0).getTitle());
            lblMyHospitalDescription.setText(hospitalList.get(0).getDescription());

            return view;
        }
    }
}
