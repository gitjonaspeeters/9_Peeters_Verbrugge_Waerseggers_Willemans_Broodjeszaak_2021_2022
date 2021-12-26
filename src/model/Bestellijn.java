package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public String getBroodjeString() {
        return broodje.getName();
    }

    public ArrayList<BelegSoort> getBeleg() {
        return beleg;
    }
    public ArrayList<String> getBelegString() {
        ArrayList<String> bel=new ArrayList<>();
        if (beleg==null) return bel;
        for (int i=0;i<beleg.size();i++) {
            bel.add(beleg.get(i).getName());
        }
        return bel;
    }


    public void removeBeleg(String beleg){
        this.beleg.remove(beleg);
    }

    public double getPrijs(){
        double r=0;
        r+=broodje.getPrijs();
        if (beleg != null&&beleg.size()>0){
            for (BelegSoort b:beleg) {
                r+=b.getPrijs();
            }

        }
        return r;
    }
}
