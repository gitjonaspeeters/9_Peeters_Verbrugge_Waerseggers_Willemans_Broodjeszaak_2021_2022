package model;

import controller.OrderViewController;
import model.bestelStates.BestellingState;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;
import model.korting.KortingFactory;
import model.observer.Observer;
import model.observer.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BestelFacade implements Subject {
    Bestelling bestelling;
    BroodjesDatabase broodjesDatabase;
    BelegDatabase belegDatabase;
    HashMap<BestellingsEvents,ArrayList<Observer>> events=new HashMap<>();
    HashMap<Integer, Bestelling>  wachtrij = new HashMap<>();

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
    public void betaal(){
        bestelling.betalen();
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

    public void verwijderBestellijn(int index){
        broodjesDatabase.getBroodje(bestellijn.getBroodje().getName()).aanpassenVoorraad(getVoorraadLijstBroodje().get(bestellijn.getBroodje().getName())+1);
        if(bestellijn.getBeleg() != null){
        for(int i = 0; i < bestellijn.getBeleg().size(); i++){
            belegDatabase.getBeleg(bestellijn.getBeleg().get(i).getName()).aanpassenVoorraad(getVoorraadLijstBeleg().get(bestellijn.getBeleg().get(i).getName()) +1);
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
    public void voegBelegToeAanBestelLijn(int index,String beleg){
        if (getVoorraadLijstBeleg().get(beleg)>0){
            belegDatabase.getBeleg(beleg).aanpassenVoorraad(getVoorraadLijstBeleg().get(beleg)-1);
            bestelling.voegBelegtoe(index,belegDatabase.getBeleg(beleg));

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
        if (broodjesDatabase.getVoorraadLijstBroodje().get(bestellijn.getBroodje().getName())==0){
            return false;
        }
        TreeMap<String,Integer> belegBestellijn=new TreeMap<>();
        if (bestellijn.getBeleg()!=null){
            for (BelegSoort beleg:bestellijn.getBeleg()) {
                if (belegBestellijn.containsKey(beleg.getName())){
                    belegBestellijn.put(beleg.getName(),belegBestellijn.get(beleg.getName())+1);
                }else {
                    belegBestellijn.put(beleg.getName(),1);
                }
            }
            for (String beleg:belegBestellijn.keySet()){
                if (belegDatabase.getVoorraadLijstBeleg().get(beleg)<belegBestellijn.get(beleg)) return false;
            }
        }

        return true;
    }
    public void aanpassenVooraadIngredientenVoorBestellijn(Bestellijn bestellijn){
        broodjesDatabase.getBroodje(bestellijn.getBroodje().getName()).aanpassenVoorraad(getVoorraadLijstBroodje().get(bestellijn.getBroodje().getName())-1);

        TreeMap<String,Integer> belegBestellijn=new TreeMap<>();
        if (bestellijn.getBeleg()!=null){
            for (BelegSoort beleg:bestellijn.getBeleg()) {
                if (belegBestellijn.containsKey(beleg.getName())){
                    belegBestellijn.put(beleg.getName(),belegBestellijn.get(beleg.getName())+1);
                }else {
                    belegBestellijn.put(beleg.getName(),1);
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
    public double getPrijsNaKorting(String korting){
        return KortingFactory.Korting(korting).BerekenKorting(bestelling);
    }

    public int setInWachtrij() {
        bestelling.setInWachtrij();
        wachtrij.put(bestelling.getVolgnr(),bestelling);
        return bestelling.getVolgnr();
    }

    public int getAantalBroodjesWachtrij(int volgnr){
        return wachtrij.get(volgnr).getBestellijnen().size();
    }

    public int getWachtrijAantalvanBroodje(int volgnr, String broodje){
        return wachtrij.get(volgnr).
    }

    public int getWachtrijAantalvanBeleg(String beleg){

    }


}
