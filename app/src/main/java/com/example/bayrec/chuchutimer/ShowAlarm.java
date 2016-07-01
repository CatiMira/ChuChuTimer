package com.example.bayrec.chuchutimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
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

        String[] temp = splitalles(alles);
        StartOrt = temp[2];
        StartOrtZeit = temp[3];
        ReiseZeit = temp[4];
        LaufZeit = temp[6];

        TextView so = (TextView) findViewById(R.id.Start);
        so.setText(StartOrt);
        TextView soz = (TextView) findViewById(R.id.StartZeit);
        soz.setText(StartOrtZeit);
        TextView rz = (TextView) findViewById(R.id.reiseZeit);
        rz.setText(ReiseZeit);
        TextView lz = (TextView) findViewById(R.id.laufZeit);
        lz.setText("Reisezeit zum Bahnhof: "+LaufZeit);
    }
    public String[] splitalles(String text){
        String[] segs = text.split(Pattern.quote("\n"));
        return segs;
    }
}
