package com.example.coronaapp;

import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class User {

    private String fName, email, phone;
    private boolean IsSick;

    public User() {}

    public boolean isSick() {
        return IsSick;
    }

    public void setSick(boolean sick) {
        IsSick = sick;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
