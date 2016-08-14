package com.kosarict.mrs.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.kosarict.mrs.R;
import com.kosarict.mrs.activity.DoctorDashboardActivity;
import com.kosarict.mrs.model.spinnerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RegisterFragment extends Fragment {
    private View layoutView;
    private FragmentActivity context;
    private EditText txtNationalCode;
    private Spinner ddlState;
    private Spinner ddlCity;
    private Spinner ddlHospitalType;
    private Spinner ddlHospital;
    private List<spinnerModel> stateModel = new ArrayList<>();
    private List<spinnerModel> cityModel = new ArrayList<>();
    private List<spinnerModel> hospitalTypeModel = new ArrayList<>();
    private List<spinnerModel> hospitalModel = new ArrayList<>();


    public RegisterFragment() {
    }


    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance() {
        return new RegisterFragment();
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
        layoutView = inflater.inflate(R.layout.fragment_register, container, false);

        initButton();
        initSpinnerData();
        initSpinner();
        initEditText();

        return layoutView;
    }

    private void initButton() {
        Button btnRegisterCancel = (Button) layoutView.findViewById(R.id.btnRegisterCancel);

        btnRegisterCancel.setOnClickListener(new View.OnClickListener() {
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
            case R.id.btnRegisterCancel:
                DoctorDashboardActivity.lblPageTitle.setText(R.string.title_activity_doctor_dashboard);
                fragment = ListFragment.newInstance();
                break;
        }

        if (fragment != null) {
            context.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment, "ListFragment")
                    .addToBackStack("ListFragment").commit();
        }
    }

    private void initSpinner() {
        ddlState = (Spinner) layoutView.findViewById(R.id.ddlState);
        ddlCity = (Spinner) layoutView.findViewById(R.id.ddlCity);
        ddlHospitalType = (Spinner) layoutView.findViewById(R.id.ddlHospitalType);
        ddlHospital = (Spinner) layoutView.findViewById(R.id.ddlHospital);

        ddlState.setAdapter(new spinnerDataAdapter());
        ddlState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerModel stateModel = (spinnerModel) parent.getSelectedItem();
                List<String> cityModelList = stateModel.getCityList();

                ddlCity.setAdapter(new spinnerDataAdapter1(cityModelList));
                ddlCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        spinnerModel stateModel = (spinnerModel) ddlState.getSelectedItem();
                        List<String> hospitalModel = stateModel.getHospitalList();

                        ddlHospital.setAdapter(new spinnerDataAdapter1(hospitalModel));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSpinnerData() {
        List<String> cityList1 = new ArrayList<>();
        List<String> cityList2 = new ArrayList<>();
        List<String> cityList3 = new ArrayList<>();

        List<String> hospitalList1 = new ArrayList<>();
        List<String> hospitalList2 = new ArrayList<>();
        List<String> hospitalList3 = new ArrayList<>();

        cityList1.add("اهواز");
        cityList1.add("خرمشهر");

        cityList2.add("تهران");

        cityList3.add("مشهد");

        hospitalList1.add("بیمارستان آریا اهواز");
        hospitalList2.add("بیمارستان شهید مدرس");
        hospitalList3.add("بیمارستان امام رضا");

        spinnerModel stateModel1 = new spinnerModel(1, "خوزستان", cityList1, hospitalList1);
        spinnerModel stateModel2 = new spinnerModel(2, "تهران", cityList2, hospitalList2);
        spinnerModel stateModel3 = new spinnerModel(3, "خراسان رضوی", cityList3, hospitalList3);

        stateModel.add(stateModel1);
        stateModel.add(stateModel2);
        stateModel.add(stateModel3);
    }

    private void initEditText() {
        txtNationalCode = (EditText) layoutView.findViewById(R.id.txtNationalCode);
    }

    private class spinnerDataAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return stateModel.size();
        }

        @Override
        public spinnerModel getItem(int position) {
            return stateModel.get(position);
        }

        @Override
        public long getItemId(int position) {
            return stateModel.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater layoutInflater = context.getLayoutInflater();
                view = layoutInflater.inflate(R.layout.spinner_item_layout, null);
            } else {
                view = convertView;
            }

            TextView text1 = (TextView) view.findViewById(R.id.text1);

            text1.setText(stateModel.get(position).getTitle());

            return view;
        }
    }

    private class spinnerDataAdapter1 extends BaseAdapter {
        List<String> list;

        public spinnerDataAdapter1(List<String> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
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
                view = layoutInflater.inflate(R.layout.spinner_item_layout, null);
            } else {
                view = convertView;
            }

            TextView text1 = (TextView) view.findViewById(R.id.text1);

            text1.setText(list.get(position));

            return view;
        }
    }
}
