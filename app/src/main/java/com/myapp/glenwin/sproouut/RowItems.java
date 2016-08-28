package com.myapp.glenwin.sproouut;

/**
 * Created by Charlotte on 8/27/2016.
 */


public class RowItems {
    private String data_names;
    private int data_pictures_id;

    public RowItems(String data_names, int data_pictures_id){
        this.data_names = data_names;
        this.data_pictures_id = data_pictures_id;
    }

    public String getData_names() {return data_names;}
    public void setData_names(String data_names){this.data_names = data_names;}
    public int getPicture_id() {return data_pictures_id;}
    public void setData_pictures_id(int data_pictures_id) {this.data_pictures_id = data_pictures_id;}
}
