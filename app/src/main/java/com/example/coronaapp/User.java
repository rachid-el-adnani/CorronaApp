package com.example.coronaapp;

import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class User {

    private String temp, fName, email, phone;
    private FirebaseUser cUser;

    public User() {}

    public User(String temp, FirebaseUser cUser) {
        this.temp = temp;
        this.cUser = cUser;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public FirebaseUser getcUser() {
        return cUser;
    }

    public void setcUser(FirebaseUser cUser) {
        this.cUser = cUser;
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
