package com.example.bayrec.chuchutimer;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createNewAlarm(View v){
        Intent newWindow = new Intent(MainActivity.this, SelectTrain.class);
        this.startActivity(newWindow);
    }
}
