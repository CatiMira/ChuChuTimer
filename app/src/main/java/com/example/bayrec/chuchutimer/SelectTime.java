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
import java.util.regex.Pattern;

public class SelectTime extends AppCompatActivity {

    private static String Departure;
    private static String Arrival;
    private static String Year;
    private static String Month;
    private static String Day;
    private static String Hour;
    private static String Minute;
    private static String duration;

    String start = "";
    String end = "";

    private static String TAG = "Orte";
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        Intent intent = getIntent();

        Departure = intent.getStringExtra("departure");
        Arrival = intent.getStringExtra("arrival");
        Year = intent.getStringExtra("year");
        Month = intent.getStringExtra("month");
        Day = intent.getStringExtra("day");
        Hour = intent.getStringExtra("hour");
        Minute = intent.getStringExtra("minute");

        String trainleave = Year+"-"+Month+"-"+Day+"T"+Hour+"%3A"+Minute;

        TextView so = (TextView) findViewById(R.id.Start);
        so.setText(Departure);
        TextView zo = (TextView) findViewById(R.id.Destination);
        zo.setText(Arrival);

        mDialog = ProgressDialog.show(this, "Suche Zeiten", "Bitte warten...");
        getTimes("http://transport.opendata.ch/v1/connections?from="+Departure+"&to="+Arrival+"&datetime="+trainleave+"&limit=4");
    }
    // die Zeiten an für die Verbindung werden geholt
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
                duration = jsonObject.get("duration").toString();

                JSONObject fromtime = new JSONObject(from);
                JSONObject totime = new JSONObject(to);

                start = fromtime.get("departure").toString();
                end = totime.get("arrival").toString();

                start = splitDate(start);
                end = splitDate(end);
                duration = splitDuration(duration);

                times.add(start+" - "+end+"\nDauer: "+duration);

                ListView time = (ListView) findViewById(R.id.Times);
                time.setAdapter(times);

                AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(), NewAlarm.class);
                        String selected = parent.getItemAtPosition(position).toString();
                        Toast toast=Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
                        toast.show();
                        intent.putExtra("zeiten", selected);
                        intent.putExtra("startort", Departure);
                        intent.putExtra("zielort", Arrival);
                        intent.putExtra("jahr", Year);
                        intent.putExtra("monat", Month);
                        intent.putExtra("tag", Day);
                        intent.putExtra("dauer", duration);
                        startActivity(intent);
                    }
                };
                time.setOnItemClickListener(mListClickedHandler);
            }
        } catch (JSONException e) {e.printStackTrace();}
    }
    // das Datum wird getrennt
    public String splitDate(String text){
        String[] segs = text.split(Pattern.quote("T"));
        text = segs[1];
        segs = text.split(Pattern.quote(":00+"));
        return segs[0];
    }
    // die Dauer wird getrennt
    public String splitDuration(String text){
        text = text.substring(0,text.length()-3);
        text = text.substring(3);
        return text;
    }
}
