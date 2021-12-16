package model;

import model.bestelStates.*;

import java.util.ArrayList;

public class Bestelling {

    private ArrayList<Bestellijn> bestellijnen;
    private BestellingState Afgesloten;
    private BestellingState Betaald;
    private BestellingState InBereiding;
    private BestellingState Inbestelling;
    private BestellingState InWachtrij;
    private BestellingState InWacht;
    private BestellingState Annuleren;
    private BestellingState Klaargemaakt;
    private BestellingState state;
    private int volgnr=0;



    public Bestelling() {
        Afgesloten = new Afgesloten(this);
        Betaald = new Betaald(this);
        InBereiding = new Inbereiding(this);
        Inbestelling = new InBestelling(this);
        InWachtrij =  new InWachtrij(this);
        InWacht = new InWacht(this);
        Annuleren = new Annuleren(this);
        Klaargemaakt=new Klaargemaakt(this);
        bestellijnen=new ArrayList<>();
        this.state = InWacht;
        this.bestellijnen=new ArrayList<>();
        this.volgnr+=1;

    }

    public int getVolgnr() {
        return volgnr;
    }


    public BestellingState getAnnuleren() {
        return Annuleren;
    }

    public void setAnnuleren(BestellingState annuleren) {
        volgnr-=1;
        Annuleren = annuleren;
    }

    public ArrayList<Bestellijn> getBestellijnen() {
        return bestellijnen;
    }

    public void setBestellijnen(ArrayList<Bestellijn> bestellijnen) {
        this.bestellijnen = bestellijnen;
    }

    public BestellingState getAfgesloten() {
        return Afgesloten;
    }

    public void setAfgesloten(BestellingState afgesloten) {
        Afgesloten = afgesloten;
    }

    public BestellingState getBetaald() {
        return Betaald;
    }

    public void setBetaald(BestellingState betaald) {
        Betaald = betaald;
    }

    public BestellingState getInBereiding() {
        return InBereiding;
    }
    public BestellingState getKlaargemaakt(){
        return Klaargemaakt;
    }

    public void setInBereiding(BestellingState inBereiding) {
        InBereiding = inBereiding;
    }

    public BestellingState getInbestelling() {
        return Inbestelling;
    }

    public void setInbestelling(BestellingState inbestelling) {
        Inbestelling = inbestelling;
    }

    public BestellingState getInWachtrij() {
        return InWachtrij;
    }

    public void setInWachtrij(BestellingState inWachtrij) {
        InWachtrij = inWachtrij;
    }

    public BestellingState getInWacht() {
        return InWacht;
    }

    public void setInWacht(BestellingState inWacht) {
        InWacht = inWacht;
    }

    public BestellingState getState() {
        return state;
    }

    public void setState(BestellingState state){
        this.state = state;
    }
    public void voegBestelLijnToe(String broodje){
        Bestellijn bestellijn=new Bestellijn(broodje);
        bestellijnen.add(bestellijn);
    }

    public void voegBelegtoe(Bestellijn bestellijn, String beleg){
        for (Bestellijn b:bestellijnen) {
            if (b.equals(bestellijn)){
                b.addBeleg(beleg);
            }

        }

    }

    public Bestellijn getBestellijn(String broodje){
        for(Bestellijn bestellijn : bestellijnen){
            if(bestellijn.broodje.equals(broodje)){
                return bestellijn;
            }
        }
        return null;
    }



    public ArrayList<Bestellijn> getBestelLijnen() {
        return bestellijnen;
    }

    public void verwijderBestellijn(int i) {
        bestellijnen.remove(i);
    }
}
