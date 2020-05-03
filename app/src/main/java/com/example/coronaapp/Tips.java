package com.example.coronaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.coronaapp.HelperClasses.TipsAdapter;
import com.example.coronaapp.HelperClasses.TpisHelperClass;

import java.util.ArrayList;

public class Tips extends AppCompatActivity {

    //Vars
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tips);

        //Hooks
        recyclerView = findViewById(R.id.tipscarousel);

        //Methods
        RecyclerView();



    }

    private void RecyclerView() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<TpisHelperClass> tpisHelperClassArry = new ArrayList<>();

        tpisHelperClassArry.add(new TpisHelperClass(R.drawable.stay, "Stay at home.", "Unless you have something VERY important to do!"));
        tpisHelperClassArry.add(new TpisHelperClass(R.drawable.mask, "Put on your mask.", "Always, and don't forget that it's only a personal uses and it should be changed every 4 hours of use!"));
        tpisHelperClassArry.add(new TpisHelperClass(R.drawable.wash, "Wash your hands.", "Regally after going or touching something outside!"));
        tpisHelperClassArry.add(new TpisHelperClass(R.drawable.red, "Keep it clean.", "All the clothes, shoes and items used out should stay out!"));
        tpisHelperClassArry.add(new TpisHelperClass(R.drawable.idea, "Keep us updated.", "Fill our form daily so we can help you take the right decision at the right time!"));


        adapter = new TipsAdapter(tpisHelperClassArry);
        recyclerView.setAdapter(adapter);


    }
}
