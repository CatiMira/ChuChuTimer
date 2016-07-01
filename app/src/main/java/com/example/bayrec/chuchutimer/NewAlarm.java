package com.example.bayrec.chuchutimer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class NewAlarm extends AppCompatActivity {

    private static String StartOrt;
    private static String ZielOrt;
    private static String StartOrtZeit;
    private static String ZielOrtZeit;
    private static String Datum;
    private static String Dauer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alarm);

        Intent intent = getIntent();

        String Jahr = intent.getStringExtra("jahr");
        String Monat = intent.getStringExtra("monat");
        String Tag = intent.getStringExtra("tag");
        Datum = Jahr+"-"+Monat+"-"+Tag;
        Dauer = intent.getStringExtra("dauer");

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

    private String[] spliteTime(String text){

        return text.split(Pattern.quote(":"));
    }
    public void saveOnClick(View v){
        EditText et = (EditText) findViewById(R.id.NeedTime);
        int value = Integer.valueOf(String.valueOf(et.getText()));

        String[] temp =  spliteTime(StartOrtZeit);
        int hour = Integer.valueOf(temp[0]);
        int minute = Integer.valueOf(temp[1]);
        minute += hour*60;
        minute -= value;

        hour = minute;
        minute = minute % 60;
        hour -= minute;
        hour /=60;
        String combinedDate = combine(hour, minute);

        saveAlarm(StartOrt, ZielOrt, StartOrtZeit, ZielOrtZeit, Dauer, Datum, combinedDate);

        Intent intent = new Intent( NewAlarm.this, MainActivity.class);
        intent.putExtra("LaufZeit", value);
        this.startActivity(intent);
    }

    public void saveAlarm(String StartOrt, String ZielOrt, String StartOrtZeit, String ZielOrtZeit, String Dauer, String Datum, String combinedDate){
        SharedPreferences preferences = getSharedPreferences("app_name", Context.MODE_PRIVATE);

        String alarm = "\n"+Datum+"\n"+StartOrt+" - "+ZielOrt+"\n"+StartOrtZeit+" - "+ZielOrtZeit+"Dauer: "+Dauer+"\nWecker: "+combinedDate+"\n";

        int anzahlWecker = preferences.getInt("Wecker", 0);
        anzahlWecker++;
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(String.valueOf(anzahlWecker), alarm);
        editor.putInt("Wecker", anzahlWecker);
        editor.commit();
    }

    private String combine(int hour, int minute){
        return hour+":"+minute;
    }
}
