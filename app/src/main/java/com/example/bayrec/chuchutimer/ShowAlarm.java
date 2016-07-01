package com.example.bayrec.chuchutimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.widget.TextView;

import java.util.regex.Pattern;

public class ShowAlarm extends AppCompatActivity {

    private static String Place;
    private static String Time;
    // wie lange die Zugfahrt dauert
    private static String drivetime;
    // wie lange man zum Bahnhof läuft
    private static String walktime;

    // Erstellung der Aktivity
    // Fügt den Text in die TextViews ein
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_alarm);

        Intent intent = getIntent();
        String all = intent.getStringExtra("all");

        String[] temp = splitalles(all);
        Place = temp[2];
        Time = temp[3];
        drivetime = temp[4];
        walktime = temp[6];

        TextView so = (TextView) findViewById(R.id.place);
        so.setText(Place);
        TextView soz = (TextView) findViewById(R.id.time);
        soz.setText(Time);
        TextView rz = (TextView) findViewById(R.id.driveTime);
        rz.setText(drivetime);
        TextView lz = (TextView) findViewById(R.id.walkTime);
        lz.setText("Reisezeit zum Bahnhof: "+walktime);
    }

    // Methode zum trennen der Angaben im String all
    public String[] splitalles(String text){
        String[] segs = text.split(Pattern.quote("\n"));
        return segs;
    }
}
