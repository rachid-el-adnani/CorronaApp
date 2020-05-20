package com.example.coronaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Diagnosis extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Vars
    Spinner fever, cough, breath, sour;
    Button submit, show;
    String FValue, CValue, BValue, SValue;
    ArrayAdapter<CharSequence> fAdapter, cAdapter, bAdapter, sAdapter;
    DataBaseHelper DB;
    Dialog dialog;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        //Creating a new channel for the notification
        createChannel();

        //Hooks
        fever  =  findViewById(R.id.temp);
        cough  =  findViewById(R.id.cough);
        breath =  findViewById(R.id.breathing);
        sour   =  findViewById(R.id.sour);
        show = findViewById(R.id.show);
        submit = findViewById(R.id.submit);
        DB = new DataBaseHelper(this);
        dialog = new Dialog(this);




        //Setting Adapters for the spinners
        fAdapter = ArrayAdapter.createFromResource(this, R.array.temp, android.R.layout.simple_spinner_dropdown_item);
        cAdapter = ArrayAdapter.createFromResource(this, R.array.cough, android.R.layout.simple_spinner_dropdown_item);
        bAdapter = ArrayAdapter.createFromResource(this, R.array.breath, android.R.layout.simple_spinner_dropdown_item);
        sAdapter = ArrayAdapter.createFromResource(this, R.array.sour, android.R.layout.simple_spinner_dropdown_item);

        //Providing data inside the spinners
        fever.setAdapter(fAdapter);
        cough.setAdapter(cAdapter);
        breath.setAdapter(bAdapter);
        sour.setAdapter(sAdapter);
        //Extracting values
        fever.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 FValue = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cough.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 CValue = parent.getItemAtPosition(position).toString();
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });
        breath.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BValue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SValue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });









        //Storing Fever state in database to use it in the chart
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = Calendar.getInstance().getTime().toString();
                boolean isInserted = DB.insertData(date, FValue);
                if (isInserted) {
                    Toast.makeText(Diagnosis.this, "inserted in database successfully. You'll be notified like this time TOMORROW!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Diagnosis.this, "Something went wrong... ", Toast.LENGTH_SHORT).show();
                }

                Intent in = new Intent(Diagnosis.this, ReminderBrodcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(Diagnosis.this, 0, in, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                long timeClicked = System.currentTimeMillis();
                long timeToWait = 1000 * 86400;

                //Setting when the alarm will go off and what action to preform
                alarmManager.set(AlarmManager.RTC_WAKEUP,
                        timeClicked + timeToWait,
                        pendingIntent);

                //Disabling the button until 24hrs has passed
                submit.setEnabled(false);

                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                submit.setEnabled(true);
                            }
                        });
                    }
                }, timeToWait);

                //Creating a global var
                Global global = (Global) getApplicationContext();
                //Making the test
                if (FValue.toLowerCase().equals("hot") && BValue.toLowerCase().equals("yes")){
                    //Setting the the state in the global var
                    global.setState("Sick");
                    //Showing danger pop up
                    dialog.setContentView(R.layout.danger_pop_up);
                    TextView txtClose = dialog.findViewById(R.id.txtclose);
                    txtClose.setText("M");
                    Button btnFollow = dialog.findViewById(R.id.call);
                    txtClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    //And traaaa
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();

                }else if ( FValue.toLowerCase().equals("normal") && BValue.toLowerCase().equals("no") && CValue.toLowerCase().equals("no") && SValue.toLowerCase().equals("no")){
                    //Setting the the state in the global var
                    global.setState("Safe");
                    //Showing warning pop up
                    dialog.setContentView(R.layout.safe_pop_up);
                    TextView txtClose = dialog.findViewById(R.id.txtclose);
                    txtClose.setText("M");
                    txtClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    //And traaaa
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();

                }else
                    //Setting the the state in the global var
                    global.setState("Better check");
                    //Showing warning pop up
                    dialog.setContentView(R.layout.warning_pop_up);
                    TextView txtClose = dialog.findViewById(R.id.txtclose);
                    txtClose.setText("M");
                    txtClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    //And traaaa
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
            }
        });


        //Sowing data
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getData();
                if (res.getCount() == 0){
                    //Will display some error
                    showMessage("Oops!", "To show your health progression you must pass the test first.");
                    return;
                }
                //getting all data into a buffer
                StringBuffer buffer = new StringBuffer();
                int state;
                ArrayList<String> states = new ArrayList<>();
                while (res.moveToNext()){
                    if (res.getString(1).toLowerCase().equals("cold")){
                        state = 32;
                    }else if (res.getString(1).toLowerCase().equals("normal")){
                        state = 36;
                    }else if (res.getString(1).toLowerCase().equals("a little bit cold")){
                        state = 34;
                    }else if (res.getString(1).toLowerCase().equals("a little bit hot")){
                        state = 38;
                    }else
                        state = 40;
                    buffer.append("Date ").append(res.getString(0)).append("\n");
                    buffer.append("Temperature : ").append(state).append("\n\n");
                    states.add(String.valueOf(state));
                }

                //show all data
                String data = buffer.toString();
                showMessage("Data", data);

                //Passing the states to the health activity
                Intent i = new Intent(Diagnosis.this, Health.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("data", states);
                i.putExtras(bundle);
                startActivity(i);

            }
        });























    }

    //The pop-up
    public void showMessage(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }















    //Creating the channel
    public void createChannel() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "healthChannel";
            String description = "Channel for Health Chart";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Health", name, importance);
            channel.setDescription(description);

            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String val = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}

