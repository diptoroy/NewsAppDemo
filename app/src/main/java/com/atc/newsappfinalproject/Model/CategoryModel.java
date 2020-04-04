package com.atc.newsappfinalproject.Model;

public class CategoryModel {

    private String ca_name;
    private int ca_img;

    public CategoryModel() {
    }

    public CategoryModel(String ca_name, int ca_img) {
        this.ca_name = ca_name;
        this.ca_img = ca_img;
    }

    public String getCa_name() {
        return ca_name;
    }

    public void setCa_name(String ca_name) {
        this.ca_name = ca_name;
    }

    public int getCa_img() {
        return ca_img;
    }

    public void setCa_img(int ca_img) {
        this.ca_img = ca_img;
    }
}
