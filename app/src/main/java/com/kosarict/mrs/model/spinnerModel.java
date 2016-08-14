package com.kosarict.mrs.model;

import java.util.List;

/**
 * Created by Sadegh-Pc on 8/13/2016.
 */
public class spinnerModel {
    private int id;
    private String title;
    private List<String> cityList;
    private List<String> hospitalList;

    public spinnerModel(int id, String title, List<String> cityList, List<String> hospitalList) {
        this.id = id;
        this.title = title;
        this.cityList = cityList;
        this.hospitalList = hospitalList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getCityList() {
        return cityList;
    }

    public void setCityList(List<String> cityList) {
        this.cityList = cityList;
    }

    public List<String> getHospitalList() {
        return hospitalList;
    }

    public void setHospitalList(List<String> hospitalList) {
        this.hospitalList = hospitalList;
    }
}
