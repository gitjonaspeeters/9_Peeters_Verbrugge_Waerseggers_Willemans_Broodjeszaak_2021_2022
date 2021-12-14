package model;

import java.util.ArrayList;

public class Bestellijn {
    String broodje;
    ArrayList<String> beleg;

    public Bestellijn(String broodje, ArrayList<String> beleg) {
        this.broodje = broodje;
        this.beleg = beleg;
    }

    public Bestellijn(String broodje) {
        this.broodje = broodje;
    }
    public void addBeleg(String beleg){
        this.beleg.add(beleg);
    }
    public void removeBeleg(String beleg){
        this.beleg.remove(beleg);
    }
}
