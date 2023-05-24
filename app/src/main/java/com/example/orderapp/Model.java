package com.example.orderapp;

public class Model {


    String mshoeName;
    String mshoeDetail;
    int mshoePhoto;

    public Model(String mshoeName, String mshoeDetail, int mshoePhoto) {
        this.mshoeName = mshoeName;
        this.mshoeDetail = mshoeDetail;
        this.mshoePhoto = mshoePhoto;
    }




    public String getmshoeName() {
        return mshoeName;
    }

    public String getmshoeDetail() {
        return mshoeDetail;
    }

    public int getmshoePhoto() {
        return mshoePhoto;
    }
}
