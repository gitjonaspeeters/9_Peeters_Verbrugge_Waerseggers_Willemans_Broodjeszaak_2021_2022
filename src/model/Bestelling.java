package model;

import java.util.ArrayList;

public class Bestelling {
    private ArrayList<Bestellijn> bestellijnen;

    public Bestelling(ArrayList<Bestellijn> bestellijnen) {
        this.bestellijnen = bestellijnen;
    }

    public Bestelling() {
        bestellijnen=new ArrayList<>();
    }
    public void voegBestelLijnToe(String broodje){
        Bestellijn bestellijn=new Bestellijn(broodje);
    }

    public ArrayList<Bestellijn> getBestelLijnen() {
        return bestellijnen;
    }
}
