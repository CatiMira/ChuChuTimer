package com.example.bayrec.chuchutimer;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by bayrec on 30.06.2016.
 */
public class SetAlarm {
    public void saveAlarm(String StartOrt, String ZielOrt, String StartZeit, String EndZeit, String ReiseZeit, String Datum, String LosLaufZeit) {
        File file = new File("Alarms.txt");
        String alarm = StartOrt+"§"+ZielOrt+"§"+StartZeit+"§"+EndZeit+"§"+ReiseZeit+"§"+Datum+"§"+LosLaufZeit+"\n";

        if (!file.exists()){
            try{
                file.createNewFile();
            } catch (IOException e) { e.printStackTrace(); }
        }

        try {
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(alarm.getBytes());
        } catch (FileNotFoundException e) { Log.v("Exception", "File write failed: " + e.toString()); } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        try{
            fos = new FileOutputStream(file);
            fos.write(alarm.getBytes());
            fos.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
    public void getAlarm(){
    }
}
