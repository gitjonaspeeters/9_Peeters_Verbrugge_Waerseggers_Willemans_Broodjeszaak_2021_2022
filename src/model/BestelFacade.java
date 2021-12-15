package model;

import controller.OrderViewController;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;
import model.observer.Observer;
import model.observer.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class BestelFacade implements Subject {
    Bestelling bestelling;
    BroodjesDatabase broodjesDatabase;
    BelegDatabase belegDatabase;
    HashMap<BestellingsEvents,Observer> events=new HashMap<>();

    public BestelFacade() throws Exception {
        for (Observer o:observers){
            if (o.getClass().toString().equalsIgnoreCase("OrderViewController"))
                events.put(BestellingsEvents.START_NIEUWE_BESTELLING,o);
        }

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
    public int startNieuweBestelling(){
        bestelling = new Bestelling();
        return bestelling.getVolgnr();
    }

    public void voegBestelLijnToe(String broodje){
        if (getVoorraadLijstBroodje().get(broodje)>0){
            broodjesDatabase.getBroodje(broodje).aanpassenVoorraad(getVoorraadLijstBroodje().get(broodje)-1);
            bestelling.voegBestelLijnToe(broodje);
        }
    }
    public void voegBelegToeAanBestelLijn(String broodje,String beleg){
        if (getVoorraadLijstBeleg().get(beleg)>0){
            belegDatabase.getBeleg(beleg).aanpassenVoorraad(getVoorraadLijstBeleg().get(beleg)-1);
            bestelling.voegBelegtoe(broodje,beleg);
        }
    }
    public ArrayList<Bestellijn> getBestelLijnen(){return bestelling.getBestelLijnen();}
    public TreeMap<String,Integer> getVoorraadLijstBroodje(){
        return broodjesDatabase.getVoorraadLijstBroodje();
    }
    public TreeMap<String,Integer> getVoorraadLijstBeleg(){
        return belegDatabase.getVoorraadLijstBeleg();
    }


}
