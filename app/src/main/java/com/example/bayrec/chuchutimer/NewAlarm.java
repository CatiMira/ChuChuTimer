package com.example.bayrec.chuchutimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.regex.Pattern;

public class NewAlarm extends AppCompatActivity {

    private static String StartOrt;
    private static String ZielOrt;
    private static String StartOrtZeit;
    private static String ZielOrtZeit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alarm);

        Intent intent = getIntent();
        String zeiten = intent.getStringExtra("zeiten");
        StartOrt = intent.getStringExtra("startort");
        ZielOrt = intent.getStringExtra("zielort");

        String[] temp = splitDate(zeiten);
        StartOrtZeit = temp[0];
        ZielOrtZeit = temp[1];

        TextView so = (TextView) findViewById(R.id.Start);
        so.setText(StartOrt);
        TextView zo = (TextView) findViewById(R.id.Ziel);
        zo.setText(ZielOrt);
        TextView soz = (TextView) findViewById(R.id.StartZeit);
        soz.setText(StartOrtZeit);
        TextView zoz = (TextView) findViewById(R.id.EndZeit);
        zoz.setText(ZielOrtZeit);
    }

    private String[] splitDate(String text) {
        String[] temp = text.split(Pattern.quote("Dauer"));
        return temp[0].split(Pattern.quote(" - "));

    }
}
