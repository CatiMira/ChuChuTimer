package com.example.bayrec.chuchutimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowAlarm extends AppCompatActivity {

    private static String StartOrt;
    private static String ZielOrt;
    private static String StartOrtZeit;
    private static String ZielOrtZeit;
    private static String ReiseZeit;
    private static String LaufZeit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_alarm);

        TextView so = (TextView) findViewById(R.id.Start);
        so.setText(StartOrt);
        TextView zo = (TextView) findViewById(R.id.Ziel);
        zo.setText(ZielOrt);
        TextView soz = (TextView) findViewById(R.id.StartZeit);
        soz.setText(StartOrtZeit);
        TextView zoz = (TextView) findViewById(R.id.EndZeit);
        zoz.setText(ZielOrtZeit);
        TextView rz = (TextView) findViewById(R.id.reiseZeit);
        rz.setText(LaufZeit);
        TextView lz = (TextView) findViewById(R.id.laufZeit);
        lz.setText(ReiseZeit);
    }
}
