package com.example.bayrec.chuchutimer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAlarm();
    }

    public void getAlarm() {
        ArrayAdapter wecker = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        SharedPreferences preferences = getSharedPreferences("app_name", Context.MODE_PRIVATE);

        int anzahlWecker = preferences.getInt("Wecker", 0);

        for (int i = 1; i < anzahlWecker + 1; i++) {
            String alles = preferences.getString(String.valueOf(i), "");

            wecker.add(alles);

            ListView alarme = (ListView) findViewById(R.id.Alarms);
            alarme.setAdapter(wecker);

            AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), ShowAlarm.class);
                    String selected = parent.getItemAtPosition(position).toString();
                    intent.putExtra("alles", selected);
                    startActivity(intent);
                }
            };
            alarme.setOnItemClickListener(mListClickedHandler);
        }
    }

    public void createNewAlarm(View v) {
        Intent newWindow = new Intent(MainActivity.this, SelectTrain.class);
        this.startActivity(newWindow);
    }
}

