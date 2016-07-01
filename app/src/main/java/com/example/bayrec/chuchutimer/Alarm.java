package com.example.bayrec.chuchutimer;

/**
 * Created by bayrec on 30.06.2016.
 */
public class Alarm {
    private String StartOrt;
    private String ZielOrt;
    private String StartZeit;
    private String EndZeit;
    private String ReiseZeit;
    private String Datum;
    private String LosLaufZeit;

    public String setStartOrt()                 { return StartOrt; }
    public String setZielOrt()                  { return ZielOrt; }
    public String setStartZeit()                { return StartZeit; }
    public String setEndZeit()                  { return EndZeit; }
    public String setReiseZeit()                { return ReiseZeit; }
    public String setDatum()                    { return Datum; }
    public String setLosLaufZeit()              { return LosLaufZeit; }

    public void getStartOrt(String SO)          { StartOrt = SO; }
    public void getZielOrt(String ZO)           { ZielOrt = ZO; }
    public void getStartZeit(String SZ)         { StartZeit = SZ; }
    public void getEndZeit(String EZ)           { EndZeit = EZ; }
    public void getReiseZeit(String RZ)         { ReiseZeit = RZ; }
    public void getDatum(String D)              { Datum= D; }
    public void getLosLaufZeit(String LLZ)      { LosLaufZeit= LLZ; }

    public Alarm(String StartOrt, String ZielOrt, String StartZeit, String EndZeit, String ReiseZeit, String Datum, String LosLaufZeit){
        this.StartOrt = StartOrt;
        this.ZielOrt = ZielOrt;
        this.StartZeit = StartZeit;
        this.EndZeit = EndZeit;
        this.ReiseZeit = ReiseZeit;
        this.Datum = Datum;
        this.LosLaufZeit = LosLaufZeit;
    }
    public void saveAlarm(){
        SetAlarm setAlarm = new SetAlarm();
        setAlarm.saveAlarm(StartOrt, ZielOrt, StartZeit, EndZeit, ReiseZeit, Datum, LosLaufZeit);
    }
}

