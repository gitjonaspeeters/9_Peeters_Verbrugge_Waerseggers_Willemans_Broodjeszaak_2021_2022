package model;

import model.database.BelegDatabase;
import model.database.BroodjesDatabase;
import model.observer.Observer;
import model.observer.Subject;

import java.util.ArrayList;
import java.util.TreeMap;

public class BestelFacade implements Subject {
    Bestelling bestelling;
    BroodjesDatabase broodjesDatabase;
    BelegDatabase belegDatabase;

    public BestelFacade() throws Exception {
        this.bestelling = new Bestelling();
        this.broodjesDatabase = new BroodjesDatabase("XLSBroodje");
        this.belegDatabase = new BelegDatabase("XLSBeleg");
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() throws Exception {
        for (Observer o : observers) {
            o.update();

        }
    }
    public void voegBestelLijnToe(String broodje){bestelling.voegBestelLijnToe(broodje);};
    public ArrayList<Bestellijn> getBestelLijnen(){return bestelling.getBestelLijnen();}
    public TreeMap<String,Integer> getVoorraadLijstBroodje(){
        return broodjesDatabase.getVoorraadLijstBroodje();
    }
    public TreeMap<String,Integer> getVoorraadLijstBeleg(){
        return belegDatabase.getVoorraadLijstBeleg();
    }

}
