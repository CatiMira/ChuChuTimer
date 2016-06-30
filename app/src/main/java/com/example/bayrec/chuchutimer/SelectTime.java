package com.example.bayrec.chuchutimer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

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

    private static String TAG = "Orte";
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        Intent intent = getIntent();

        StartOrt = intent.getStringExtra("departure");
        ZielOrt = intent.getStringExtra("arrival");

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

        mDialog = ProgressDialog.show(this, "Suche Orte", "Bitte warten...");
        getTimes("http://transport.opendata.ch/v1/connections?from="+StartOrt+"&to="+ZielOrt+"&limit=3");
    }
    public void getTimes(String url){
        new AsyncTask<String, String, String>(){
            @Override
            protected String doInBackground(String[] badi) {
                String msg = "";
                try{
                    URL url = new URL(badi[0]);
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
        TextView sz1 = (TextView) findViewById(R.id.StartZeit1);
        TextView zz1 = (TextView) findViewById(R.id.EndZeit1);
        TextView rz = (TextView) findViewById(R.id.Dauer1);

        String start = "";
        String ende = "";

        try {
            JSONObject  jsonRootObject = new JSONObject(jsonstring);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("from");
            JSONArray jsonArray2 = jsonRootObject.optJSONArray("to");

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject jsonObject2 = jsonArray2.getJSONObject(i);

                start += jsonObject.optString("departure").toString();

                ende += jsonObject2.optString("arrival").toString();
            }
            sz1.setText(start);
            zz1.setText(ende);
        } catch (JSONException e) {e.printStackTrace();}
    }
}
