package com.example.bayrec.chuchutimer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAlarm();
    }

    // Alle gespeicherten Alarme holen (mit getSharedPreferences) und diese dann ausgeben
    public void getAlarm() {
        ArrayAdapter ringer = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        SharedPreferences preferences = getSharedPreferences("app_name", Context.MODE_PRIVATE);

        int numberOfringers = preferences.getInt("Ringer", 0);

        for (int i = 1; i < numberOfringers + 1; i++) {
            String all = preferences.getString(String.valueOf(i), "");

            ringer.add(all);

            ListView alarms = (ListView) findViewById(R.id.Alarms);
            alarms.setAdapter(ringer);

            AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), ShowAlarm.class);
                    String selected = parent.getItemAtPosition(position).toString();
                    intent.putExtra("all", selected);
                    startActivity(intent);
                }
            };
            alarms.setOnItemClickListener(mListClickedHandler);
        }
    }

    // Methode zum ImageButton
    public void createNewAlarm(View v) {
        Intent newWindow = new Intent(MainActivity.this, SelectTrain.class);
        this.startActivity(newWindow);
    }
}

