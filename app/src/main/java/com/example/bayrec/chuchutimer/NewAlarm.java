package com.example.bayrec.chuchutimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NewAlarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alarm);
        TextView StartOrt = new TextView(this);
        StartOrt = (TextView) findViewById(R.id.Start);
        StartOrt.setText("Bern");
    }
}
