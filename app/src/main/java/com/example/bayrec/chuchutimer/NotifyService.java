package com.example.bayrec.chuchutimer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

import com.example.bayrec.chuchutimer.MainActivity;

public class NotifyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate()
    {
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this.getApplicationContext(), NewAlarm.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,0);

        Notification mNotify = new Notification.Builder(this)
                .setContentTitle("IHR ZUG")
                .setContentText("Sie müssen auf den Zug")
                .setContentIntent(pIntent)
                .setSound(sound)
                .addAction(0, "IHR ZUG", pIntent)
                .build();
        mNM.notify(1, mNotify);
    }
}