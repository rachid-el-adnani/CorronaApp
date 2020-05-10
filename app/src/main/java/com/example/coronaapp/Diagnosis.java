package com.example.coronaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Diagnosis extends AppCompatActivity {

    //Vars
    Spinner fever, cough, breath, sour;
    Button submit;
    ArrayAdapter<String> fAdapter, cAdapter, bAdapter, sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        //Hooks
        fever  =  findViewById(R.id.temp);
        cough  =  findViewById(R.id.cough);
        breath =  findViewById(R.id.breathing);
        sour   =  findViewById(R.id.sour);
        submit = findViewById(R.id.submit);

        //Setting Adapters
        fAdapter = new ArrayAdapter<String>(Diagnosis.this, android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.temp));
        cAdapter = new ArrayAdapter<String>(Diagnosis.this, android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.cough));
        bAdapter = new ArrayAdapter<String>(Diagnosis.this, android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.breath));
        sAdapter = new ArrayAdapter<String>(Diagnosis.this, android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.sour));

        //Providing data inside the spinners
        fever.setAdapter(fAdapter);
        cough.setAdapter(cAdapter);
        breath.setAdapter(bAdapter);
        sour.setAdapter(sAdapter);

    }
}
