package model;

import controller.OrderViewController;
import model.bestelStates.BestellingState;
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
        if (events!=null || events.size() == 0){
            for (Observer o : events.get(bestellingsEvents)) {
                o.update();
            }
        }

    }


    public int annuleer(){
        int res = bestelling.annuleerBestelling();
        try {
            notifyObservers(BestellingsEvents.ANNULLEER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void verwijderBestellijn(Bestellijn bestellijn){
        broodjesDatabase.getBroodje(bestellijn.getBroodje()).aanpassenVoorraad(getVoorraadLijstBroodje().get(bestellijn.getBroodje())+1);
        if(bestellijn.getBeleg() != null){
        for(int i = 0; i < bestellijn.getBeleg().size(); i++){
            belegDatabase.getBeleg(bestellijn.getBeleg().get(i)).aanpassenVoorraad(getVoorraadLijstBeleg().get(bestellijn.getBeleg().get(i)) +1);
        }}
        bestelling.verwijderBestelling(bestellijn);
        try {
            notifyObservers(BestellingsEvents.VERWIJDER_BESTELLIJN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int startNieuweBestelling(){

       if (bestelling==null){
           bestelling = new Bestelling();

       }else {

           bestelling=new Bestelling(this.bestelling);
       }
        try {
            notifyObservers(BestellingsEvents.START_NIEUWE_BESTELLING);
        } catch (Exception e) {
            e.printStackTrace();
        }



        return bestelling.startNieuweBestelling();



    }

    public void voegBestelLijnToe(String broodje){
        if (getVoorraadLijstBroodje().get(broodje)>0){
            broodjesDatabase.getBroodje(broodje).aanpassenVoorraad(getVoorraadLijstBroodje().get(broodje)-1);
            bestelling.voegBestellijnToeState(broodjesDatabase.getBroodje(broodje));
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
            bestelling.voegBelegtoe(bestellijn,belegDatabase.getBeleg(beleg));

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

    public void setState(BestellingState state){
        bestelling.setState(state);
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
        if (bestellijn.getBeleg()!=null){
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
        }

        return true;
    }
    public void aanpassenVooraadIngredientenVoorBestellijn(Bestellijn bestellijn){
        broodjesDatabase.getBroodje(bestellijn.getBroodje()).aanpassenVoorraad(getVoorraadLijstBroodje().get(bestellijn.getBroodje())-1);

        TreeMap<String,Integer> belegBestellijn=new TreeMap<>();
        if (bestellijn.getBeleg()!=null){
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


    }

    public void aflsluitenBestelling() {
        bestelling.aflsuitenBestelling();
    }

    public double getPrijs() {
        return bestelling.getPrijs();
    }
}
