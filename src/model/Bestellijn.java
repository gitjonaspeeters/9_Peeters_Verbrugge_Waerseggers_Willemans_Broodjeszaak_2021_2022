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
        if (this.beleg==null){
            this.beleg=new ArrayList<>();
        }
        this.beleg.add(beleg);
    }

    public String getBroodje() {
        return broodje;
    }

    public ArrayList<String> getBeleg() {
        return beleg;
    }

    public void removeBeleg(String beleg){
        this.beleg.remove(beleg);
    }
}
