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

        //Creating the tip cards
        tpisHelperClassArry.add(new TpisHelperClass(R.drawable.stay, "Stay at home.", "Unless you have something VERY important to do!"));
        tpisHelperClassArry.add(new TpisHelperClass(R.drawable.mask, "Put on your mask.", "Always, and don't forget that it's only a personal uses and it should be changed every 4 hours of use!"));
        tpisHelperClassArry.add(new TpisHelperClass(R.drawable.wash, "Wash your hands.", "Regally after going or touching something outside!"));
        tpisHelperClassArry.add(new TpisHelperClass(R.drawable.red, "Keep it clean.", "All the clothes, shoes and items used out should stay out!"));
        tpisHelperClassArry.add(new TpisHelperClass(R.drawable.namaste, "Namaste.", "No more handshakes, hugs or kisses when you meet people. Create new ways to greet reach other!"));
        tpisHelperClassArry.add(new TpisHelperClass(R.drawable.distance, "Social Distancing.", "When you're out, make sure to maintain the minimal distance which is at least 2 meters!"));
        tpisHelperClassArry.add(new TpisHelperClass(R.drawable.sneeze, "Take Cover.", "When you sneeze or cough cover your mouth and nose with your elbow or a tissue."));
        tpisHelperClassArry.add(new TpisHelperClass(R.drawable.avoid, "Avoid.", "Touching your eyes, nose or mouth until you wash your hands"));
        tpisHelperClassArry.add(new TpisHelperClass(R.drawable.mine, "Mine!.", "Keep your PERSONAL stuff PERSONAL! Your mask, tissue and anything you uses when going out should be used by you and you only."));
        tpisHelperClassArry.add(new TpisHelperClass(R.drawable.sport, "While at home.", "Don't forget to so some exercises, and take care of your health and nutrition. Corona will leave eventually."));
        tpisHelperClassArry.add(new TpisHelperClass(R.drawable.idea, "Keep us updated.", "Fill our form daily so we can help you take the right decision at the right time!"));


        adapter = new TipsAdapter(tpisHelperClassArry);
        recyclerView.setAdapter(adapter);


    }
}
