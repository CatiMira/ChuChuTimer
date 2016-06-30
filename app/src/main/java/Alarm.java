/**
 * Created by bayrec on 30.06.2016.
 */
public class Alarm {
    private String StartOrt;
    private String ZielOrt;
    private String StartZeit;
    private String EndZeit;
    private String ZugAbfahrZeit;
    private String ReiseZeit;

    public String setStartOrt()                 { return StartOrt; }
    public String setZielOrt()                  { return ZielOrt; }
    public String setStartZeit()                { return StartZeit; }
    public String setEndZeit()                  { return EndZeit; }
    public String setZugAbfahrZeit()            { return ZugAbfahrZeit; }
    public String setReiseZeit()                { return ReiseZeit; }

    public void getStartOrt(String SO)          { StartOrt = SO; }
    public void getZielOrt(String ZO)           { ZielOrt = ZO; }
    public void getStartZeit(String SZ)         { StartZeit = SZ; }
    public void getEndZeit(String EZ)           { EndZeit = EZ; }
    public void getZugAbfahrZeit(String ZAZ)    { ZugAbfahrZeit = ZAZ; }
    public void getReiseZeit(String RZ)         { ReiseZeit = RZ; }

    public Alarm(String StartOrt, String ZielOrt, String StartZeit, String EndZeit, String ZugAbfahrZeit, String ReiseZeit){
        this.StartOrt = StartOrt;
        this.ZielOrt = ZielOrt;
        this.StartZeit = StartZeit;
        this.EndZeit = EndZeit;
        this.ZugAbfahrZeit = ZugAbfahrZeit;
        this.ReiseZeit = ReiseZeit;
    }
}
