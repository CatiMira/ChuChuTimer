package com.example.bayrec.chuchutimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
        StartOrt = intent.getStringExtra("departure");
        ZielOrt = intent.getStringExtra("arrival");
        StartOrtZeit = intent.getStringExtra("");
        ZielOrtZeit = intent.getStringExtra("");

        TextView so = (TextView) findViewById(R.id.Start);
        so.setText(StartOrt);
        TextView zo = (TextView) findViewById(R.id.Ziel);
        zo.setText(ZielOrt);
        TextView soz = (TextView) findViewById(R.id.StartZeit);
        soz.setText(StartOrtZeit);
        TextView zoz = (TextView) findViewById(R.id.EndZeit);
        zoz.setText(ZielOrtZeit);
    }

    public void setTime(View v)
    {
        String content = String.valueOf(R.id.TimeEditText);

        if(content.isEmpty() == false)
        {
            int TimeToGo = Integer.parseInt(StartOrtZeit);
        }
    }
}
