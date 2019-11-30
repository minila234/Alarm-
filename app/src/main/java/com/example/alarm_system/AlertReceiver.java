package com.example.alarm_system;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class AlertReceiver extends BroadcastReceiver {

    public  static Ringtone ring;
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();

        int notificationId = intent.getIntExtra("notificationId", 0);
        String message = intent.getStringExtra("todo");
        int toneID = intent.getIntExtra("toneid",0);
        Log.d("toneid","toneid");
        // When notification is tapped, call MainActivity.
        Intent mainIntent = new Intent(context, Quiz.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);

        NotificationManager notificationManager =(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);

// Prepare notification.
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("It's Time!")
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(false)
                .setContentIntent(contentIntent)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_ALL);

        // Notify
        notificationManager.notify(notificationId, builder.build());


if(toneID==1) {
    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
    ring = RingtoneManager.getRingtone(context, notification);
    ring.play();
}
else if(toneID== 2){
    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
    ring = RingtoneManager.getRingtone(context, notification);
    ring.play();
}
else if(toneID== 3){
    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    ring = RingtoneManager.getRingtone(context, notification);
    ring.play();
}
else{
    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
    ring = RingtoneManager.getRingtone(context, notification);
    ring.play();
}


    }






}
