package com.example.coronaapp;

import android.app.Application;

public class Global extends Application {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
