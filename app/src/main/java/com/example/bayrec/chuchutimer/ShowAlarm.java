package com.example.bayrec.chuchutimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.regex.Pattern;

public class ShowAlarm extends AppCompatActivity {

    private static String StartOrt;
    private static String StartOrtZeit;
    private static String ReiseZeit;
    private static String LaufZeit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_alarm);

        Intent intent = getIntent();
        String alles = intent.getStringExtra("alles");
        StartOrt = intent.getStringExtra("Orte");
        StartOrtZeit = intent.getStringExtra("Zeit");
        ReiseZeit = intent.getStringExtra("ReiseZeit");
        LaufZeit = intent.getStringExtra("LaufZeit");

        TextView so = (TextView) findViewById(R.id.Start);
        so.setText(StartOrt);
        TextView soz = (TextView) findViewById(R.id.StartZeit);
        soz.setText(StartOrtZeit);
        TextView lz = (TextView) findViewById(R.id.reiseZeit);
        lz.setText(ReiseZeit);
    }
}
