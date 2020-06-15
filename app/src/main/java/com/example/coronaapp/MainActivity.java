package com.example.coronaapp;

import android.Manifest;
import android.app.ActivityOptions;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

import io.grpc.util.ForwardingLoadBalancer;

import static android.net.Uri.parse;

public class MainActivity extends AppCompatActivity {

    CardView DCard, PCard, MCard, CCard, TACard, LCard, MCall, scanner;
    private FirebaseFirestore fStore;
    private FirebaseAuth fAuth;
    private String userId;
    Global ID;
    TextView title;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hooks
        MCall = findViewById(R.id.medicsCall);
        scanner = findViewById(R.id.scanner);
        ////////////////////////////////////////
        DCard = findViewById(R.id.diagnosisCard);
        PCard = findViewById(R.id.profileCard);
        MCard = findViewById(R.id.mapCard);
        CCard = findViewById(R.id.carefulCard);
        TACard = findViewById(R.id.tipsCard);
        LCard = findViewById(R.id.logoutCard);

        title = findViewById(R.id.tipsCardtxt);



        //fire base
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();





        //Medics Call
        MCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Medics();
            }
        });
        //Scanner trigger
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, QrScanner.class);
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                startActivity(i, activityOptions.toBundle());
            }
        });
        ///////////////////////////////////////////////////

        //Diagnosis
        DCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Diagnosis.class);
                i.putExtra("CURRENT_USER", fAuth.getCurrentUser().getUid());
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                startActivity(i, activityOptions.toBundle());
            }
        });

        //Profile trigger
        PCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Home.class);
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                startActivity(i, activityOptions.toBundle());
            }
        });


        MCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                startActivity(i, activityOptions.toBundle());
            }
        });

       /* // Careful
        CCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Careful.class);
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                startActivity(i, activityOptions.toBundle());
            }
        });*/

        //Tips and Advices
        TACard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Tips.class);

                Pair[] pairs = new Pair[1];
                pairs [0] = new Pair<View, String>(title, "titleTransition");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                startActivity(i, activityOptions.toBundle());
            }
        });


        //Log out
        LCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(v);
            }
        });

    }




    //logging out
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();


    }




    public void Medics() {
        String police_num = "";

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] {Manifest.permission.CALL_PHONE}, 1);
        } else {

            String dial = "tel:"+police_num;
            startActivity(new Intent(Intent.ACTION_CALL, parse(dial)));

        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Medics();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
