package com.example.coronaapp;

import android.app.Application;

public class Global extends Application {
    private String ID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
