package com.example.bayrec.chuchutimer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
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

    String laufen;
    private static String LosLaufen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        laufen = intent.getStringExtra("LauZeit");
        getAlarm();
    }

    public void getAlarm(){
        ArrayAdapter wecker = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        SharedPreferences preferences = getSharedPreferences("app_name", Context.MODE_PRIVATE);

        int anzahlWecker = preferences.getInt("Wecker", 0);

            for(int i=1; i < anzahlWecker+1; i++){
                String alles = preferences.getString(String.valueOf(i), "");

                // 2016-7-2
                // Lausanne - Bern
                // 10:24 - 11:51
                // Dauer: 01:27
                // Wecker: 10:14

                String[] temp = splitalles(alles);
                final String Datum = temp[1];
                final String Ort = temp[2];
                final String Zeit = temp[3];
                final String ReiseZeit = temp[4];
                final String Wecker = temp[5];

                wecker.add(Datum+"\n"+Ort+"\n"+Zeit+"\n"+Wecker);

                ListView alarme = (ListView) findViewById(R.id.Alarms);
                alarme.setAdapter(wecker);

                AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(), ShowAlarm.class);
                        String selected = parent.getItemAtPosition(position).toString();
                        Toast toast=Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
                        toast.show();
                        intent.putExtra("alles", selected);
                        intent.putExtra("Orte", Ort);
                        intent.putExtra("Datum", Datum);
                        intent.putExtra("Zeit", Zeit);
                        intent.putExtra("ReiseZeit", ReiseZeit);
                        intent.putExtra("LaufZeit", laufen);
                        startActivity(intent);
                    }
                };
                alarme.setOnItemClickListener(mListClickedHandler);
            }
    }

    public void createNewAlarm(View v){
        Intent newWindow = new Intent(MainActivity.this, SelectTrain.class);
        this.startActivity(newWindow);
    }
    public String[] splitalles(String text){
        String[] segs = text.split(Pattern.quote("\n"));
        return segs;
    }
}
