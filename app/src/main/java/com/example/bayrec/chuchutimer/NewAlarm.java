package com.example.bayrec.chuchutimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
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
import java.util.Calendar;
import java.util.regex.Pattern;

public class NewAlarm extends AppCompatActivity {

    private static String StartPlace;
    private static String DestinationPlace;
    private static String StartPlaceTime;
    private static String DestinationPlaceTime;
    private static String Date;
    private static String Duration;
    private static String Walk;
    private PendingIntent pendingIntent;

    // Fügt dan TextViews die Texte hinzu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alarm);

        Intent intent = getIntent();

        String Jahr = intent.getStringExtra("jahr");
        String Monat = intent.getStringExtra("monat");
        String Tag = intent.getStringExtra("tag");
        Date = Jahr+"-"+Monat+"-"+Tag;
        Duration = intent.getStringExtra("dauer");

        String zeiten = intent.getStringExtra("zeiten");
        StartPlace = intent.getStringExtra("startort");
        DestinationPlace = intent.getStringExtra("zielort");

        String[] temp = splitDate(zeiten);
        StartPlaceTime = temp[0];
        DestinationPlaceTime = temp[1];

        TextView so = (TextView) findViewById(R.id.Start);
        so.setText(StartPlace);
        TextView zo = (TextView) findViewById(R.id.Ziel);
        zo.setText(DestinationPlace);
        TextView soz = (TextView) findViewById(R.id.StartZeit);
        soz.setText(StartPlaceTime);
        TextView zoz = (TextView) findViewById(R.id.EndZeit);
        zoz.setText(DestinationPlaceTime);
    }

    // Methode um das Datum zu trennen
    private String[] splitDate(String text) {
        String[] temp = text.split(Pattern.quote("Dauer"));
        return temp[0].split(Pattern.quote(" - "));
    }

    // Methode um die Zeit zu trennen
    private String[] spliteTime(String text){

        return text.split(Pattern.quote(":"));
    }

    // Methode wenn man auf den Speichern Button klickt
    // Wandelt die Dauer um und Speichert die Daten
    public void saveOnClick(View v){
        EditText et = (EditText) findViewById(R.id.NeedTime);
        int value = Integer.valueOf(String.valueOf(et.getText()));

        Walk = et.getText().toString();

        String[] temp =  spliteTime(StartPlaceTime);
        int hour = Integer.valueOf(temp[0]);
        int minute = Integer.valueOf(temp[1]);
        minute += hour*60;
        minute -= value;

        hour = minute;
        minute = minute % 60;
        hour -= minute;
        hour /=60;
        String combinedDate = combine(hour, minute);

        // Speichern der Daten
        saveAlarm(StartPlace, DestinationPlace, StartPlaceTime, DestinationPlaceTime, Duration, Date, combinedDate);

        // Initialisierung des Alarms
        doAlarm(hour, minute, Date);

        Intent intent = new Intent( NewAlarm.this, MainActivity.class);
        this.startActivity(intent);
    }

    // den Alarm in den SharedPreferences speichern
    public void saveAlarm(String StartPlace, String DestinationPlace, String StartPlaceTime, String DestinationPlaceTime, String Duration, String Date, String combinedDate){
        SharedPreferences preferences = getSharedPreferences("app_name", Context.MODE_PRIVATE);

        String alarm = "\n"+Date+"\n"+StartPlace+" - "+DestinationPlace+"\n"+StartPlaceTime+" - "+DestinationPlaceTime+"Dauer: "+Duration+"\nWecker: "+combinedDate+"\n"+Walk+" min"+"\n";

        int numberOfringers = preferences.getInt("Ringer", 0);
        numberOfringers++;
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(String.valueOf(numberOfringers), alarm);
        editor.putInt("Ringer", numberOfringers);
        editor.commit();
    }

    // die Loslaufzeit kombinieren
    private String combine(int hour, int minute){
        return hour+":"+minute;
    }

    // Initialisiert den Alarm nach Datum und Zeit
    private void doAlarm(int hour, int minute, String date) {
        String[] spliteDate = date.split(Pattern.quote("-"));

        Intent myIntent = new Intent(this, NotifyService.class);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.YEAR, Integer.valueOf(spliteDate[0]));
        calendar.set(Calendar.MONTH, Integer.valueOf(spliteDate[1])-1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(spliteDate[2]));

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60*60*24, pendingIntent);

        Toast.makeText(NewAlarm.this,"Alarm aktiviert", Toast.LENGTH_LONG).show();
    }
}
