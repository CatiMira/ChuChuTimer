package com.example.bayrec.chuchutimer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.regex.Pattern;

public class SelectTime extends AppCompatActivity {

    private static String StartOrt;
    private static String ZielOrt;
    private static String Jahr;
    private static String Monat;
    private static String Tag;
    private static String Stunde;
    private static String Minute;

    String start = "";
    String ende = "";

    private static String TAG = "Orte";
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        Intent intent = getIntent();

        StartOrt = intent.getStringExtra("departure");
        ZielOrt = intent.getStringExtra("arrival");
        Jahr = intent.getStringExtra("year");
        Monat = intent.getStringExtra("month");
        Tag = intent.getStringExtra("day");
        Stunde = intent.getStringExtra("hour");
        Minute = intent.getStringExtra("minute");

        String abfahrt = Jahr+"-"+Monat+"-"+Tag+"T"+Stunde+"%3A"+Minute;

        TextView so = (TextView) findViewById(R.id.Start);
        so.setText(StartOrt);
        TextView zo = (TextView) findViewById(R.id.Ziel);
        zo.setText(ZielOrt);

        mDialog = ProgressDialog.show(this, "Suche Zeiten", "Bitte warten...");
        getTimes("http://transport.opendata.ch/v1/connections?from="+StartOrt+"&to="+ZielOrt+"&datetime="+abfahrt+"&limit=4");
    }
    public void getTimes(String url){
        new AsyncTask<String, String, String>(){
            @Override
            protected String doInBackground(String[] times) {
                String msg = "";
                try{
                    URL url = new URL(times[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    int code = conn.getResponseCode();
                    mDialog.dismiss();
                    Log.i(TAG, Integer.toString(code));
                    msg = IOUtils.toString(conn.getInputStream());
                }
                catch (Exception e){
                    Log.v(TAG, e.toString());
                }
                return msg;
            }
            public void onPostExecute(String result){
                parseJson(result);
            }
        }.execute(url);
    }
    private void parseJson(String jsonstring){



        ArrayAdapter times = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        try {
            JSONObject  jsonRootObject = new JSONObject(jsonstring);

            JSONArray jsonArray = jsonRootObject.optJSONArray("connections");

            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String from = jsonObject.get("from").toString();
                String to = jsonObject.get("to").toString();
                String duration = jsonObject.get("duration").toString();

                JSONObject fromtime = new JSONObject(from);
                JSONObject totime = new JSONObject(to);

                start = fromtime.get("departure").toString();
                ende = totime.get("arrival").toString();

                start = splitAll(start);
                ende = splitAll(ende);

                times.add(start+" - "+ende+"\nDauer: "+duration);

                ListView zeit = (ListView) findViewById(R.id.zeiten);
                zeit.setAdapter(times);

                AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(), NewAlarm.class);
                        String selected = parent.getItemAtPosition(position).toString();
                        Toast toast=Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
                        toast.show();
                        intent.putExtra("zeiten", selected);
                        startActivity(intent);
                    }
                };
                zeit.setOnItemClickListener(mListClickedHandler);
            }
        } catch (JSONException e) {e.printStackTrace();}
    }
    public String splitAll(String text){
        String[] segs = text.split(Pattern.quote("T"));
        text = segs[1];
        segs = text.split(Pattern.quote(":00+"));
        return segs[0];
    }
}
