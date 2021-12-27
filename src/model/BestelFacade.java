package model;

import controller.OrderViewController;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.bestelStates.BestellingState;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;
import model.database.LoadSaveStrategies.LoadSaveStrategyEnum;
import model.database.LoadSaveStrategies.LoadSaveStrategyFactory;
import model.database.Settings;
import model.korting.KortingFactory;
import model.observer.Observer;
import model.observer.Subject;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BestelFacade implements Subject {
    Bestelling bestelling;
    BroodjesDatabase broodjesDatabase;
    BelegDatabase belegDatabase;
    HashMap<BestellingsEvents,ArrayList<Observer>> events=new HashMap<>();
    HashMap<Integer, Bestelling>  wachtrij = new HashMap<>();

    public BestelFacade() throws Exception {
        this.broodjesDatabase = new BroodjesDatabase(LoadSaveStrategyEnum.valueOf(Settings.getInstance().getProperty("formaat").toUpperCase(Locale.ROOT) +  "Broodje").name() );

        this.belegDatabase = new BelegDatabase(LoadSaveStrategyEnum.valueOf(Settings.getInstance().getProperty("formaat").toUpperCase(Locale.ROOT)+ "Beleg").name());
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
        if (events!=null || events.size() != 0){
            for (Observer o : events.get(bestellingsEvents)) {
                o.update();
            }
        }

    }

    public BroodjesDatabase getBroodjesDatabase() {
        return broodjesDatabase;
    }

    public BelegDatabase getBelegDatabase() {
        return belegDatabase;
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
        broodjesDatabase.getBroodje(bestelling.getBestellijn(index).getBroodje().getName()).aanpassenVoorraad(getVoorraadLijstBroodje().get(bestelling.getBestellijn(index).getBroodje().getName())+1);
        if(bestelling.getBestellijn(index).getBeleg() != null){
            for(int i = 0; i < bestelling.getBestellijn(index).getBeleg().size(); i++){
                belegDatabase.getBeleg(bestelling.getBestellijn(index).getBeleg().get(i).getName()).aanpassenVoorraad(getVoorraadLijstBeleg().get(bestelling.getBestellijn(index).getBeleg().get(i).getName()) +1);
            }}
        bestelling.verwijderBestelling(bestelling.getBestellijn(index));
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
            bestelling.voegBelegtoe(bestelling.getBestellijn(index),belegDatabase.getBeleg(beleg));

            try{
                notifyObservers(BestellingsEvents.VOEG_BELEG_TOE);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public ArrayList<Bestellijn> getBestelLijnen(){
        return bestelling.getBestelLijnen();


    }
    public TreeMap<String,Integer> getVoorraadLijstBroodje(){
        return broodjesDatabase.getVoorraadLijstBroodje();
    }
    public TreeMap<String,Integer> getVoorraadLijstBeleg(){
        return belegDatabase.getVoorraadLijstBeleg();
    }
    public TreeMap<String,Integer> getVerkochtLijstBroodje(){
        return broodjesDatabase.getVerkochtLijstBroodje();
    }
    public TreeMap<String,Integer> getVerkochtLijstBeleg(){ return belegDatabase.getVerkochtLijstBeleg();
    }

    public void setState(BestellingState state){
        bestelling.setState(state);
    }


    public void voegIdentiekeBestelLijnToe(int bestellijn) {
        if (genoegIngredientenVoorBestellijn(bestelling.getBestellijn(bestellijn))){
            bestelling.voegIdentiekeBestelling(bestelling.getBestellijn(bestellijn));
            aanpassenVooraadIngredientenVoorBestellijn(bestelling.getBestellijn(bestellijn));
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


    public void setInWachtrij() {
        bestelling.setInWachtrij();
        wachtrij.put(bestelling.getVolgnr(),bestelling);
    }

    public ArrayList<Integer> volgnummers(){
        ArrayList<Integer> integers=new ArrayList<>();
        for (Integer i:wachtrij.keySet()) {
            integers.add(i);
        }
        return integers;
    }
    public int getAantalBestellingen(){
        return wachtrij.size();
    }
    public int getAantalBroodjesWachtrij(int volgnr){

        return wachtrij.get(volgnr).getBestellijnen().size();
    }

    public void verwijderBestellingInWachtrij(int volgnr){
        wachtrij.remove(volgnr);
        try {
            notifyObservers(BestellingsEvents.VERWIJDER_UIT_WACHTRIJ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Bestellijn> getSameBestellijnen(int volgnr){
            ArrayList<Bestellijn> newList = new ArrayList<Bestellijn>();
            for (Bestellijn element : wachtrij.get(volgnr).getBestellijnen()) {
                if (!newList.contains(element)) {
                    newList.add(element);
                }
            }

            return newList;
    }

    public String toStringWachtrij(int volgnr) {
        String result= "";
        for(Bestellijn b : getSameBestellijnen(volgnr)){
            result += "" + Collections.frequency(wachtrij.get(volgnr).getBestellijnen(), b) + "x " + b.toString() + "\n";
        }
        return result;
    }

    public ArrayList<String> getWachtrijBroodje(int volgnr){
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < wachtrij.get(volgnr).getBestellijnen().size(); i++) {
            //System.out.println(wachtrij.get(volgnr).getBestellijnen().get(i).getBroodje().getName());
            result.add(wachtrij.get(volgnr).getBestellijnen().get(i).getBroodje().getName());
        }
        return result;
    }

    public ArrayList<String> getBelegWachtrij(int volgnr, String broodje) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < wachtrij.get(volgnr).getBestellijnen().size(); i++) {
            for (int j = 0; j < wachtrij.get(volgnr).getBestellijnen().get(i).beleg.size(); j++) {
                if (wachtrij.get(volgnr).getBestellijnen().get(i).equals(broodje)) {
                    result.add(wachtrij.get(volgnr).getBestellijnen().get(i).beleg.get(j).getName());
                }
            }
        }
        return result;
    }


    public void aanpassenVoorraad(String formaat) {
        System.out.println(formaat);
        String formaatBroodje = formaat+"Broodje";
        try {
            BroodjesDatabase broodjesDatabase1 = new BroodjesDatabase(formaatBroodje);
            for (String b : broodjesDatabase.getBroodjes().keySet()) {
                broodjesDatabase.getBroodjes().get(b).setVerkocht(broodjesDatabase1.getBroodjes().get(b).getVerkocht() + (broodjesDatabase1.getVoorraadLijstBroodje().get(b) - broodjesDatabase.getVoorraadLijstBroodje().get(b)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String formaatBeleg = formaat+"Beleg";
        try {
            BelegDatabase belegDatabase1 = new BelegDatabase(formaatBeleg);
            for (String b : belegDatabase.getBelegSoort().keySet()) {
                belegDatabase.getBelegSoort().get(b).setVerkocht(belegDatabase1.getBelegSoort().get(b).getVerkocht() + (belegDatabase1.getVoorraadLijstBeleg().get(b) - belegDatabase.getVoorraadLijstBeleg().get(b)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            File file = new File("src/bestanden/broodjes."+formaat.toLowerCase());
            LoadSaveStrategyFactory.createLoadSaveStrategy(formaatBroodje).save(file, broodjesDatabase.getBroodjes());
            File fileBeleg = new File("src/bestanden/beleg."+formaat.toLowerCase());
            LoadSaveStrategyFactory.createLoadSaveStrategy(formaatBeleg).save(fileBeleg, belegDatabase.getBelegSoort());
            notifyObservers(BestellingsEvents.ZET_IN_WACHTRIJ);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}



