package com.example.coronaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    CardView PCard, MCard, HCard, CCard, TACard, LCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hooks
        PCard  = findViewById(R.id.profileCard);
        MCard  = findViewById(R.id.mapCard);
        HCard  = findViewById(R.id.myhealthCard);
        CCard  = findViewById(R.id.carefulCard);
        TACard = findViewById(R.id.tipsCard);
        LCard  = findViewById(R.id.logoutCard);

        //profile trigger
        PCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Home.class));
            }
        });


    }




}
