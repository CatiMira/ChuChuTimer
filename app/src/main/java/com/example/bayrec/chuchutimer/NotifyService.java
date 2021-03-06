package com.example.bayrec.chuchutimer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

public class NotifyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // Erstellt die Meldung, welche beim läuten des Alarms erscheint
    public void onCreate()
    {
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,0);

        Notification mNotify = new Notification.Builder(this)
                .setContentTitle("Steps")
                .setContentText("Log your steps for today")
                .setContentIntent(pIntent)
                .setSound(sound)
                .addAction(0, "YOUR TRAIN!!!", pIntent)
                .build();
        mNM.notify(1, mNotify);
    }
}