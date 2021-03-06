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
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;

public class SelectTrain extends AppCompatActivity
{
    private String departure, arrival;
    private int year, month, day;
    private int hour, minute;

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
    // Methode beim klicken auf den Suchen Button
    public void checkTrain(View v)
    {
        EditText et = (EditText) findViewById(R.id.FromEditText);
        departure = et.getText().toString();

        et = (EditText) findViewById(R.id.ToEditText);
        arrival = et.getText().toString();

        if (departure.isEmpty() || arrival.isEmpty()){
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
        else
            searchTrains(v);
    }
    // wenn die Eingabefelder nicht leer sind
    public void searchTrains(View v){
        Intent intent = new Intent(SelectTrain.this, SelectTime.class);

        intent.putExtra("departure", departure);
        intent.putExtra("arrival", arrival);
        intent.putExtra("year", String.valueOf(year));
        intent.putExtra("month", String.valueOf(month));
        intent.putExtra("day", String.valueOf(day));
        intent.putExtra("hour", String.valueOf(hour));
        intent.putExtra("minute", String.valueOf(minute));

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
            return new DatePickerDialog(this, dpickerListner, year, month-1, day);
        if(id == 1)
            return new TimePickerDialog(this, tpickerListner, hour, minute, true);

        return null;
    }

    private void UpdateDate(int yearNow, int monthNow, int dayNow)
    {
        year = yearNow;
        month = monthNow+1;
        day = dayNow;
        TextView tv = (TextView) findViewById(R.id.DateTextEditor);
        tv.setText(day + "." + month + "." +year);
    }

    private void UpdateTime(int hourNow, int minuteNow)
    {
        TextView tv = (TextView) findViewById(R.id.TimeEditText);
        hour = hourNow;
        minute = minuteNow;
        tv.setText(hour + ":" + minute);
    }

}

