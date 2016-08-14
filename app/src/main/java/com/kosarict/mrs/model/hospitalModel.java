package com.kosarict.mrs.model;

/**
 * Created by Sadegh-Pc on 8/13/2016.
 */
public class hospitalModel {
    private String title;
    private String description;

    public hospitalModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
