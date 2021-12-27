package model;

import java.util.*;

public class Bestellijn {
    Broodje broodje;
    ArrayList<BelegSoort> beleg;

    public Bestellijn(Broodje broodje, ArrayList<BelegSoort> beleg) {
        this.broodje = broodje;
        this.beleg = beleg;
    }

    @Override
    public String toString() {
        return broodje + ": " + getSameBeleg();
    }

    public String getSameBeleg(){
        String result= "";
        Set<BelegSoort> allebeleg = new HashSet();
        if (beleg != null) {

            allebeleg.addAll(beleg);
            for (BelegSoort x : allebeleg) {
                result += "" + Collections.frequency(beleg, x) + "x "+x.getName() + ", ";
            }
        }
        return result;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bestellijn)) return false;
        Bestellijn that = (Bestellijn) o;
        return Objects.equals(getBroodje(), that.getBroodje()) && Objects.equals(getBeleg(), that.getBeleg());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBroodje(), getBeleg());
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
