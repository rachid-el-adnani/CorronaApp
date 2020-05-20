package com.example.coronaapp;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBrodcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        //Making tha app clickable
        Intent i = new Intent(context, Diagnosis.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);



        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Health")
                .setSmallIcon(R.drawable.ic_assignment)
                .setContentTitle("Some title")
                .setContentText("Don't forget to appointment!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        final NotificationManagerCompat nmc = NotificationManagerCompat.from(context);

        nmc.notify(200, builder.build());
    }
}
