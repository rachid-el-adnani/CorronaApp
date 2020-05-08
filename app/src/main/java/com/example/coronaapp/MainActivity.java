package com.example.coronaapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    CardView DCard, PCard, MCard, HCard, CCard, TACard, LCard;
    private FirebaseFirestore fStore;
    ImageView MCall;
    private FirebaseAuth fAuth;
    private String userId;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hooks
        MCall = findViewById(R.id.medicsCall);
        ////////////////////////////////////////
        DCard = findViewById(R.id.diagnosisCard);
        PCard = findViewById(R.id.profileCard);
        MCard = findViewById(R.id.mapCard);
        HCard = findViewById(R.id.myhealthCard);
        CCard = findViewById(R.id.carefulCard);
        TACard = findViewById(R.id.tipsCard);
        LCard = findViewById(R.id.logoutCard);



        //fire base
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        final FirebaseUser user = fAuth.getCurrentUser();




        //Medics Call
        MCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Medics();
            }
        });
    ///////////////////////////////////////////////////

        //Diagnosis
        DCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), Diagnosis.class));
            }
        });

        //Profile trigger
        PCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Home.class));
            }
        });


        //Health
        HCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), Health.class));
            }
        });

        // Careful
        CCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), Careful.class));
            }
        });

        //Tips and Advices
        TACard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Tips.class));
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
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));

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
