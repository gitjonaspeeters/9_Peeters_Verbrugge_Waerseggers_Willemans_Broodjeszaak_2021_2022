package model;

import java.util.ArrayList;

public class Bestellijn {
    Broodje broodje;
    ArrayList<BelegSoort> beleg;

    public Bestellijn(Broodje broodje, ArrayList<BelegSoort> beleg) {
        this.broodje = broodje;
        this.beleg = beleg;
    }

    public Bestellijn(Broodje broodje) {
        this.broodje = broodje;
    }

    public void addBeleg(BelegSoort b){
        if (beleg==null){
            beleg=new ArrayList<>();
        }
        ArrayList<BelegSoort> beleg=new ArrayList<>();

        for (BelegSoort s:this.beleg) {
            beleg.add(s);
        }
        beleg.add(b);
        this.beleg=beleg;
    }

    /*public void addAll( ArrayList beleg){
        this.beleg = beleg;
    }*/

    public Broodje getBroodje() {
        return broodje;
    }

    public ArrayList<BelegSoort> getBeleg() {
        return beleg;
    }

    public void removeBeleg(String beleg){
        this.beleg.remove(beleg);
    }
}
