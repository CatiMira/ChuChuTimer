package com.example.bayrec.chuchutimer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Timer;

public class SelectTrain extends AppCompatActivity
{
    private String departure, arrival;
    private int year, month, day;
    private int hour, minute;
    // static final int DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_train);

        final Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

        final Time now = new Time();
        now.setToNow();
        hour = now.hour;
        minute = now.minute;

        UpdateDate(year, month, day);
        UpdateTime(hour, minute);
    }



    public void checkTrain(View v){

        Log.v("Melung1", departure);
        Log.v("Melung2", arrival);

        EditText et = (EditText) findViewById(R.id.FromEditText);
        departure = et.getText().toString();

        et = (EditText) findViewById(R.id.ToEditText);
        arrival = et.getText().toString();

        Log.v("Melung1", departure);
        Log.v("Melung2", arrival);

        if (departure != "" && arrival != ""){
            searchTrains(v);
        }
        else{
            Log.v("fuck", "fuck");
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Meldung");
            alertDialog.setMessage("Sie haben nicht alles ausgefüllt oder eine falsche Eingabe getätigt!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    public void searchTrains(View v){
        Intent intent = new Intent(SelectTrain.this, SelectTime.class);

        intent.putExtra("departure", departure);
        intent.putExtra("arrival", arrival);
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        intent.putExtra("day", day);
        intent.putExtra("hour", hour);
        intent.putExtra("minute", minute);

        this.startActivity(intent);
    }

    public void createCalendar(View v)
    {
        showDialog(0);
    }

    public void createTime(View v)
    {
        showDialog(1);
    }

    private DatePickerDialog.OnDateSetListener dpickerListner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int thisYear, int monthOfYear, int dayOfMonth)
        {
            UpdateDate(thisYear, monthOfYear, dayOfMonth);
        }
    };

    private TimePickerDialog.OnTimeSetListener tpickerListner
            = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {
            UpdateTime(hourOfDay, minute);
        }
    };

    protected Dialog onCreateDialog(int id)
    {
        if (id == 0)
            return new DatePickerDialog(this, dpickerListner, year, month, day);
        if(id == 1)
            return new TimePickerDialog(this, tpickerListner, hour, minute, true);

        return null;
    }

    private void UpdateDate(int year, int month, int day)
    {
        TextView tv = (TextView) findViewById(R.id.DateTextEditor);
        tv.setText(day + "." + month + "." +year);
    }

    private void UpdateTime(int hour, int minute)
    {
        TextView tv = (TextView) findViewById(R.id.TimeEditText);
        tv.setText(hour + ":" + minute);
    }

}

