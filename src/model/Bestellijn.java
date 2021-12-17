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

    public void addBeleg(String b){
        if (beleg==null){
            beleg=new ArrayList<>();
        }
        beleg.add(b);
        System.out.println(this.getBroodje()+this.getBeleg());

    }

    /*public void addAll( ArrayList beleg){
        this.beleg = beleg;
    }*/

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
