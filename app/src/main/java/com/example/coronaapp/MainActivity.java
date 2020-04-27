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
import com.google.firebase.firestore.FirebaseFirestore;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    CardView DCard, PCard, MCard, HCard, CCard, TACard, LCard, PCall, ACall;
    private FirebaseFirestore fStore;
    private FirebaseAuth fAuth;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hooks
        PCall = findViewById(R.id.policeCall);
        ACall = findViewById(R.id.ambulanceCard);
        ////////////////////////////////////////
        DCard = findViewById(R.id.diagnosisCard);
        PCard  = findViewById(R.id.profileCard);
        MCard  = findViewById(R.id.mapCard);
        HCard  = findViewById(R.id.myhealthCard);
        CCard  = findViewById(R.id.carefulCard);
        TACard = findViewById(R.id.tipsCard);
        LCard  = findViewById(R.id.logoutCard);


        //fire base
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();
        final FirebaseUser user = fAuth.getCurrentUser();
        
        
        //Profile trigger
        PCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Home.class));
            }
        });



    }



    //logging out
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

}
