package com.example.bayrec.chuchutimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SelectTime extends AppCompatActivity {

    private static String StartOrt;
    private static String ZielOrt;
    private static String StartOrtZeit1;
    private static String ZielOrtZeit1;
    private static String ZeitDauer1;
    private static String StartOrtZeit2;
    private static String ZielOrtZeit2;
    private static String ZeitDauer2;
    private static String StartOrtZeit3;
    private static String ZielOrtZeit3;
    private static String ZeitDauer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);


        TextView so = (TextView) findViewById(R.id.Start);
        so.setText(StartOrt);
        TextView zo = (TextView) findViewById(R.id.Ziel);
        zo.setText(ZielOrt);
        TextView soz1 = (TextView) findViewById(R.id.StartZeit1);
        soz1.setText(StartOrtZeit1);
        TextView soz2 = (TextView) findViewById(R.id.StartZeit2);
        soz2.setText(StartOrtZeit2);
        TextView soz3 = (TextView) findViewById(R.id.StartZeit3);
        soz3.setText(StartOrtZeit3);
        TextView zoz1 = (TextView) findViewById(R.id.EndZeit1);
        zoz1.setText(ZielOrtZeit1);
        TextView zoz2 = (TextView) findViewById(R.id.EndZeit2);
        zoz2.setText(ZielOrtZeit2);
        TextView zoz3 = (TextView) findViewById(R.id.EndZeit3);
        zoz3.setText(ZielOrtZeit3);
        TextView zd1 = (TextView) findViewById(R.id.Dauer1);
        zd1.setText(ZeitDauer1);
        TextView zd2 = (TextView) findViewById(R.id.Dauer2);
        zd2.setText(ZeitDauer2);
        TextView zd3 = (TextView) findViewById(R.id.Dauer3);
        zd3.setText(ZeitDauer3);
    }
}
