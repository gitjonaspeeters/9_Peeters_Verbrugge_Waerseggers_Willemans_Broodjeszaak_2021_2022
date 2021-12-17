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
    HashMap<BestellingsEvents,ArrayList<Observer>> events=new HashMap<>();

    public BestelFacade() throws Exception {
        this.broodjesDatabase = new BroodjesDatabase("XLSBroodje");
        this.belegDatabase = new BelegDatabase("XLSBeleg");
    }
    public void schrijfInVoorEvent(BestellingsEvents e,Observer o){
        if (events.containsKey(e)){
            events.get(e).add(o);
        }else {
            ArrayList<Observer> observers1=new ArrayList<>();
            observers1.add(o);
            events.put(e,observers1);
        }

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
    public void notifyObservers(BestellingsEvents bestellingsEvents) throws Exception {
        if (events!=null){
            for (Observer o : events.get(bestellingsEvents)) {
                o.update();
            }
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
            try {
                notifyObservers(BestellingsEvents.VOEG_BESTELLIJN_TOE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void voegBelegToeAanBestelLijn(Bestellijn bestellijn,String beleg){
        if (getVoorraadLijstBeleg().get(beleg)>0){
            belegDatabase.getBeleg(beleg).aanpassenVoorraad(getVoorraadLijstBeleg().get(beleg)-1);
            bestelling.voegBelegtoe(bestellijn,beleg);

            try{
                notifyObservers(BestellingsEvents.VOEG_BELEG_TOE);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public ArrayList<Bestellijn> getBestelLijnen(){return bestelling.getBestelLijnen();}
    public TreeMap<String,Integer> getVoorraadLijstBroodje(){
        return broodjesDatabase.getVoorraadLijstBroodje();
    }
    public TreeMap<String,Integer> getVoorraadLijstBeleg(){
        return belegDatabase.getVoorraadLijstBeleg();
    }


    public void voegIdentiekeBestelLijnToe(Bestellijn bestellijn) {
        if (genoegIngredientenVoorBestellijn(bestellijn)){
                bestelling.voegIdentiekeBestelling(bestellijn);
                aanpassenVooraadIngredientenVoorBestellijn(bestellijn);
        }
        try{
            notifyObservers(BestellingsEvents.VOEG_IDENTIEKE_BESTELLING_TOE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean genoegIngredientenVoorBestellijn(Bestellijn bestellijn){
        if (broodjesDatabase.getVoorraadLijstBroodje().get(bestellijn.getBroodje())==0){
            return false;
        }
        TreeMap<String,Integer> belegBestellijn=new TreeMap<>();
        for (String beleg:bestellijn.getBeleg()) {
            if (belegBestellijn.containsKey(beleg)){
                belegBestellijn.put(beleg,belegBestellijn.get(beleg)+1);
            }else {
                belegBestellijn.put(beleg,1);
            }
        }
        for (String beleg:belegBestellijn.keySet()){
            if (belegDatabase.getVoorraadLijstBeleg().get(beleg)<belegBestellijn.get(beleg)) return false;
        }
        return true;
    }
    public void aanpassenVooraadIngredientenVoorBestellijn(Bestellijn bestellijn){
        broodjesDatabase.getBroodje(bestellijn.getBroodje()).aanpassenVoorraad(getVoorraadLijstBroodje().get(bestellijn.getBroodje())-1);

        TreeMap<String,Integer> belegBestellijn=new TreeMap<>();
        for (String beleg:bestellijn.getBeleg()) {
            if (belegBestellijn.containsKey(beleg)){
                belegBestellijn.put(beleg,belegBestellijn.get(beleg)+1);
            }else {
                belegBestellijn.put(beleg,1);
            }
        }
        for (String beleg:belegBestellijn.keySet()){
            belegDatabase.getBeleg(beleg).aanpassenVoorraad(getVoorraadLijstBeleg().get(beleg)-belegBestellijn.get(beleg));
        }

    }
    public void debug(){
        for (Bestellijn b:bestelling.getBestellijnen()) {
            System.out.println(b.getBroodje()+": ");
            if (b.getBeleg()!=null){
                for (String s:b.getBeleg()) {
                    System.out.println("\t"+s);
                }
            }

        }
    }
}
